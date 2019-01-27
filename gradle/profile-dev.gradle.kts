val profiles = "dev"

val compile = configurations.getByName("compile")

dependencies {
    compile("org.springframework.boot:spring-boot-devtools")
}

val processResources = tasks.getByName("processResources") as ProcessResources

processResources.apply {
    filesMatching("**/application.yml") {
        filter {
            it.replace("#spring.profiles.active#", profiles)
        }
    }
}
