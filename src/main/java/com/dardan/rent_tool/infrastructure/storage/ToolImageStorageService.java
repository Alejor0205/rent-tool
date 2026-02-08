package com.dardan.rent_tool.infrastructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ToolImageStorageService {

    private final Path baseDir;

    public ToolImageStorageService(@Value("${rental.tools.image-dir}") String baseDir) {
        this.baseDir = Paths.get(baseDir).toAbsolutePath();
    }

    public String store(UUID toolId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Archivo de imagen requerido.");
        }
        String original = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getExtension(original);
        String filename = toolId + (extension.isEmpty() ? "" : "." + extension);
        try {
            Files.createDirectories(baseDir);
            Path target = baseDir.resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo guardar la imagen.", ex);
        }
    }

    public Path load(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            return null;
        }
        return Paths.get(imagePath);
    }

    private String getExtension(String filename) {
        int idx = filename.lastIndexOf('.');
        if (idx < 0) {
            return "";
        }
        return filename.substring(idx + 1).toLowerCase();
    }
}
