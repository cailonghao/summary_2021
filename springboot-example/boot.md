# @SpringBootApplication
## @componentScan
## @EnableAutoConfiguration
    - @AutoConfigurationPackage
        - @Import({Registrar.class})
    - @Import({AutoConfigurationImportSelector.class})
    - importSelector 动态注入
    - importBeanDefinitionRegister 动态注入
## @Configuration