package com.company.shop.config;


import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.defaults.Default;

public interface LangConfig extends Config {

    @Property("shop.lang")
    @Default("")
    String getLang();

    void setLang(String lang);


}
