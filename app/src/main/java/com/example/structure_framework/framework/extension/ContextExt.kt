package com.example.structure_framework.framework.extension

import android.content.Context
import java.util.Locale

/**
 * Get localization from android config
 * */
val Context.locale get():Locale = resources.configuration.locales.get(0)
