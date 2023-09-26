package com.example.structure_framework.framework.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.structure_framework.tools.unsafeLazy
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Base fragment class for all application fragments.
 *
 * @param VB [ViewBinding] instance used to create view of this fragment
 * @param VM [MvvmViewModel] type
 * @param VS [ViewState] type
 */
abstract class MvvmFragment<
        VB : ViewBinding,
        VM : MvvmViewModel<VS>,
        VS : ViewState
        > : Fragment() {

    private var viewBinding: VB? = null

    lateinit var viewModelFragment: VM

    protected abstract fun getViewModel(): VM

    protected val binding: VB
        get() = viewBinding ?: error("ViewBinding is not initialized")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createViewBinding(inflater, container).also { viewBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelFragment = getViewModel()

        setupView()
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            viewModelFragment.uiState.collect { handleState(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun setArguments(args: Bundle?) { // save arguments to saved state for using in view model
        if (args != null) {
            super.setArguments(Bundle(args).apply {
                putBundle(BUNDLE_ARGS, args) // Wrap the arguments as BUNDLE_ARGS
            })
        } else {
            super.setArguments(null)
        }
    }

    /**
     * Tries to cast this fragment ViewModel instance to passed [kClass]. If ViewModel instance
     * is not instance of [kClass] null is returned.
     *
     * @param T Type to cast ViewModel instance to
     *
     * @param kClass [KClass] of [T]
     *
     * @return Casted ViewModel if it is instance of [T], otherwise null
     */
    fun <T : Any> castViewModel(kClass: KClass<T>): T? = if (kClass.isInstance(viewModelFragment)) {
        @Suppress("UNCHECKED_CAST")
        viewModelFragment as T
    } else {
        null
    }

    /**
     * Tries to cast this fragment ViewModel instance to passed [T]. If ViewModel instance
     * is not instance of [T] null is returned.
     *
     * @param T Type to cast ViewModel instance to
     *
     * @return Casted ViewModel if it is instance of [T], otherwise null
     */
    inline fun <reified T : Any> castViewModel(): T? = castViewModel(T::class)

    /**
     * Creates [ViewBinding] instance which will be used as fragment view.
     *
     * @param inflater Layout inflater to create view binding instance.
     * @param parent Parent container for produced view
     *
     * @return View binding which will be used as fragment view
     */
    protected abstract fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): VB

    /**
     * Setup view (listeners, ...)
     * */
    protected abstract fun setupView()

    /**
     * Should be used to handle [VS] instance received from [VM] instance.
     *
     * @param state [VS] instance to handle
     */
    protected abstract fun handleState(state: VS)

    /**
     * Tries to cast parent fragment/activity ViewModel instance to [T]. If there is no
     * ViewModel instance which is instance of [T] null is returned.
     *
     * @param T Type to cast ViewModel instance to
     *
     * @return Casted parent ViewModel if it is instance of [T], otherwise null
     */
    @Suppress("RemoveExplicitTypeArguments")
    protected inline fun <reified T : Any> castSharedViewModel(): Lazy<T?> = unsafeLazy {
        parentFragment?.let {
            (parentFragment as? MvvmFragment<*, *, *>)?.castViewModel<T>()
        } ?: (activity as? MvvmActivity<*, *, *>)?.castViewModel<T>()
    }

    companion object {
        const val BUNDLE_ARGS = "bundleArgs"
    }
}