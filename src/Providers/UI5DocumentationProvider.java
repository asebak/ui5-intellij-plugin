package Providers;

import com.intellij.lang.documentation.DocumentationProviderEx;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by asebak on 9/28/2014.
 */
public class UI5DocumentationProvider extends DocumentationProviderEx {
    @Nullable
    @Override
    public PsiElement getCustomDocumentationElement(@NotNull Editor editor,
                                                    @NotNull PsiFile file,
                                                    @Nullable PsiElement element) {
        return null;
    }

    @Override
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
        return null;
    }

    @Override
    public List<String> getUrlFor(PsiElement element, PsiElement originalElement) {
        return null;
    }
}
