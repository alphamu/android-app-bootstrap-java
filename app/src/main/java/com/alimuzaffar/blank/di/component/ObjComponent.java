package com.alimuzaffar.blank.di.component;


import com.alimuzaffar.blank.di.scope.UserScope;
import dagger.Component;

@UserScope
@Component(dependencies = AppComponent.class)
public interface ObjComponent {
    void inject(Object obj);
}
