import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.variant.AndroidComponentsExtension
import java.io.File

class GenerateBackendsPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.sources.java?.addGeneratedSourceDirectory(
                project.tasks.register<GenerateBackendsTask>("${variant.name}GenerateBackendsEnum", GenerateBackendsTask::class.java) {
                    it.jniDir.set(project.layout.projectDirectory.dir("src/main/jni"))
                    it.outputFolder.set(project.layout.buildDirectory.dir("generated/backendsEnum/${variant.name}"))
                },
                GenerateBackendsTask::outputFolder
            )
        }

    }
}