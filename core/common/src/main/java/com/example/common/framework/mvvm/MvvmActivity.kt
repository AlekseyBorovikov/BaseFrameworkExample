package com.example.common.framework.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.common.tools.unsafeLazy
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Base activity class for all application activities.
 *
 * @param VM [MvvmViewModel] type
 * @param VS [ViewState] type
 */
abstract class MvvmActivity<
        VB: ViewBinding,
        VM : MvvmViewModel<VS>,
        VS : ViewState,
        > : AppCompatActivity() {

    private var viewBinding: VB? = null

    val viewModel by unsafeLazy { setupViewModel() }

    protected abstract fun setupViewModel(): VM

    protected val binding: VB
        get() = viewBinding ?: error("ViewBinding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
        viewBinding = createViewBinding(LayoutInflater.from(this)).also {
            setContentView(it.root)
        }
        setupView()
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                handleState(state)
            }
        }
    }

    /**
     * Tries to cast this activity ViewModel instance to passed [kClass]. If ViewModel instance
     * is not instance of [kClass] null is returned.
     *
     * @param T Type to cast ViewModel instance to
     *
     * @param kClass [KClass] of [T]
     *
     * @return Casted ViewModel if it is instance of [T], otherwise null
     */
    fun <T : Any> castViewModel(kClass: KClass<T>): T? = if (kClass.isInstance(viewModel)) {
        @Suppress("UNCHECKED_CAST")
        viewModel as T
    } else {
        null
    }

    /**
     * Tries to cast this activity ViewModel instance to passed [T]. If ViewModel instance
     * is not instance of [T] null is returned.
     *
     * @param T Type to cast ViewModel instance to
     *
     * @return Casted ViewModel if it is instance of [T], otherwise null
     */
    inline fun <reified T : Any> castViewModel(): T? = castViewModel(T::class)

    /**
     * Creates [ViewBinding] instance which will be used as content view for this activity.
     *
     * @param inflater Layout inflater to create view binding instance.
     *
     * @return View binding which will be used as activity content view
     */
    protected abstract fun createViewBinding(inflater: LayoutInflater): VB

    /**
     * Setup view (listeners, ...)
     * */
    protected abstract fun setupView()

    /**
     * The most important components of the Activity should be initialized here.
     * This method will be called first.
     * */
    protected abstract fun setupActivity()

    /**
     * Should be used to handle [VS] instance received from [VM] instance.
     *
     * @param state [VS] instance to handle
     */
    protected abstract fun handleState(state: VS)
}