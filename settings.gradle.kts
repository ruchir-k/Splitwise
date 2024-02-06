plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
include("src:abc")
findProject(":src:abc")?.name = "abc"
