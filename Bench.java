import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Bench {
    public static void main(String[] args) throws Exception {
        Path root = Path.of("").toAbsolutePath();
        String s = "hellohellohellohellohellohellohel";
        String[] words = {
            "a", "a", "a"
            // "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
            // "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"
        };

        System.out.println("s length = " + s.length());
        System.out.println("words = " + words.length + " copies of \"a\"");
        System.out.println();

        for (String name : List.of(
                "BruteForceOne",
                "BruteForceTwo",
                "OptimisedOne",
                "OptimisedTwo")) {
            Path classes = root.resolve("build").resolve(name);
            compile(root.resolve(name).resolve("Algorithm.java"), classes);

            try (URLClassLoader loader = new URLClassLoader(
                    new URL[] {classes.toUri().toURL()},
                    ClassLoader.getPlatformClassLoader())) {
                Class<?> algorithm = loader.loadClass("Algorithm");
                var findSubstring = algorithm.getMethod("findSubstring", String.class, String[].class);

                System.out.println(name);
                long started = System.nanoTime();
                List<?> matches = (List<?>) findSubstring.invoke(null, s, words.clone());
                double millis = (System.nanoTime() - started) / 1_000_000.0;
                System.out.printf("  %.2f ms   matches=%d%n%n", millis, matches.size());
            }
        }
    }

    private static void compile(Path source, Path classes) throws Exception {
        Files.createDirectories(classes);
        Path javac = Path.of(System.getProperty("java.home"), "bin", "javac");
        Process process = new ProcessBuilder(
                        javac.toString(), "--release", "17", "-d", classes.toString(), source.toString())
                .inheritIO()
                .start();
        if (process.waitFor() != 0) {
            throw new IllegalStateException("javac failed for " + source);
        }
    }
}


