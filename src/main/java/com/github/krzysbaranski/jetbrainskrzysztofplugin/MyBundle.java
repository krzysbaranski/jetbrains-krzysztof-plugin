package com.github.krzysbaranski.jetbrainskrzysztofplugin;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

public class MyBundle extends DynamicBundle {
    @NonNls
    private static final String BUNDLE = "messages.MyBundle";

    private static final MyBundle INSTANCE = new MyBundle();

    private MyBundle() {
        super(BUNDLE);
    }

    public static String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }

    @SuppressWarnings("unused")
    public static Supplier<String> messagePointer(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        @NotNull Supplier<String> x = INSTANCE.getLazyMessage(key, params);
        return x;
    }
}
