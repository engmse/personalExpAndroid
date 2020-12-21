package com.vpc3.personalexpensesapp.helper;

import com.akexorcist.localizationactivity.ui.LocalizationApplication;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MainApplication extends LocalizationApplication {
    @NotNull
    @Override
    public Locale getDefaultLanguage() {
        return new Locale("en");
    }
}
