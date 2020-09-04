# maven plugin

[官方实例](http://maven.apache.org/guides/plugin/guide-java-plugin-development.html)

# 生成

生成插件项目

```sh
mvn archetype:generate -DgroupId=xianzhan -DartifactId=hello-maven-plugin -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-plugin
mvn archetype:generate \
  -DgroupId=xianzhan \
  -DartifactId=hello-maven-plugin \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-plugin
```

安装

```sh
mvn install
```

运行

```sh
mvn xianzhan:hello-maven-plugin:1.0-SNAPSHOT:hello
```
