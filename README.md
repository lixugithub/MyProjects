# 常用工具类
## 在settings.gradle加入
```Java
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven {url "https://jitpack.io"}
    }
}
```
## 添加依赖
```Java
implementation 'com.github.lixugithub:MyProjects:v1.3'
```
![image](https://user-images.githubusercontent.com/21257157/156096835-c82736a1-f6e7-4348-9b4b-891e6204379e.png)


