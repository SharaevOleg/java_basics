import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static java.util.stream.Collectors.toList;

class СopyFolder {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (; ; ) {
            System.out.println("Enter the source path - ");
            File sourceFile = new File(reader.readLine());
            if (!sourceFile.exists()) {
                System.out.println("File not found");
                continue;
            }
            System.out.println("Enter the destination path - ");
            File destinationFile = new File(reader.readLine() + File.separator);
            if (!destinationFile.exists()) destinationFile.mkdirs();

            copyFolders(sourceFile.toPath(), destinationFile.toPath());
        }
    }

    static void copyFolders(Path sourse, Path destination) {
        try {
            List<Path> sources = Files.walk(sourse).collect(toList());
            List<Path> destinations = sources.stream()
                    .map(sourse::relativize)
                    .map(destination::resolve)
                    .collect(toList());
            for (int i = 0; i < sources.size(); i++) {

                Files.copy(sources.get(i), destinations.get(i), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(destinations.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
