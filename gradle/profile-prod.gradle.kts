val profiles = "prod"

val processResources = tasks.getByName("processResources") as ProcessResources

processResources.apply {
    filesMatching("**/application.yml") {
        filter {
            it.replace("#spring.profiles.active#", profiles)
        }
    }
}
