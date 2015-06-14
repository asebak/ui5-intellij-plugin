package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public abstract class UI5View {
    @Getter
    private final CodeGenerator codeGenerator = new CodeGenerator();
    @Getter
    private String extension;

    public UI5View(@NotNull String extension) {
        this.extension = extension;
    }

    /**
     * Given the application type and the module path for generation
     * @param appType
     * @param controllerPath
     * @return
     */
    public String generateCode(AppType appType, String controllerPath) {
        return codeGenerator.createViewCode(appType, controllerPath, extension);
    }
}
