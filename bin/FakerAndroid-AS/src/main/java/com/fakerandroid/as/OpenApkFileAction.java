package com.fakerandroid.as;
import com.fakerandroid.decoder.api.Transfer;
import com.fakerandroid.decoder.pipeline.TransformInvocation;
import com.intellij.ide.GeneralSettings;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.fileChooser.*;
import com.intellij.openapi.progress.BackgroundTaskQueue;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.platform.PlatformProjectOpenProcessor;
import com.intellij.ui.popup.PopupComponent;
import com.luhuiguo.chinese.ChineseUtils;
import com.luhuiguo.chinese.pinyin.PinyinFormat;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.File;
public class OpenApkFileAction extends DumbAwareAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Disposable disposable = Disposer.newDisposable();
        try {
            Project project = e.getProject();
            boolean showFiles = project != null || PlatformProjectOpenProcessor.getInstanceIfItExists() != null;
            OpenProjectFileChooserDescriptorWithAsyncIcon descriptor =
                    showFiles ? new ProjectOrFileChooserDescriptor() : new ProjectOnlyFileChooserDescriptor();
            descriptor.putUserData(PathChooserDialog.PREFER_LAST_OVER_EXPLICIT, showFiles);
            Disposer.register(disposable, descriptor);
            VirtualFile explicitPreferredDirectory = ((project != null) && !project.isDefault()) ? project.getBaseDir() : null;
            if (explicitPreferredDirectory == null) {
                if (StringUtil.isNotEmpty(GeneralSettings.getInstance().getDefaultProjectDirectory())) {
                    explicitPreferredDirectory = VfsUtil.findFileByIoFile(new File(GeneralSettings.getInstance().getDefaultProjectDirectory()), true);
                }
                else {
                    explicitPreferredDirectory = VfsUtil.getUserHomeDir();
                }
            }
            // The chooseFiles method shows a FileChooserDialog and it doesn't return control until
            // a user closes the dialog.
            // Note: this method is invoked from the main thread but chooseFiles uses a nested message
            // loop to avoid the IDE from freeze.
            FileChooser.chooseFiles(descriptor, project, explicitPreferredDirectory, files -> {
                VirtualFile apk = files.get(0);
                if(!apk.getPath().endsWith(".apk")){
                    return;
                }
                BackgroundTaskQueue backgroundTaskQueue = new BackgroundTaskQueue(project, "FackerAndroid is decoder running ");//.run();
                FakerAndroidTask smailPackerThread= new FakerAndroidTask(project,apk,"faking a apk file to AndroidStudio project");
                FakerAndroidProgressIndicator backgroundableProcessIndicator = new FakerAndroidProgressIndicator(smailPackerThread);
                backgroundTaskQueue.run(smailPackerThread, ModalityState.NON_MODAL,backgroundableProcessIndicator);
            });
        } finally {
            Disposer.dispose(disposable);
        }
    }
    class FakerAndroidTask extends Task.Backgroundable {
        VirtualFile apk;
        String outDir;
        Project project;
        public FakerAndroidTask(@Nullable Project project, VirtualFile apk,@Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String title) {
            super(project, title);
            this.apk = apk;
            File originalFile = new File(apk.getPath());
            File projectFile = new File(originalFile.getParent(), ChineseUtils.toPinyin(originalFile.getName().replace(".apk",""), PinyinFormat.TONELESS_PINYIN_FORMAT).replace(" ","-"));
            outDir = projectFile.getAbsolutePath();
            this.project = project;
        }
        @Override
        public boolean shouldStartInBackground() {
            return false;
        }

        @Override
        public void run(@NotNull ProgressIndicator progressIndicator) {
            //TODO  you need a FakerAndroid.jar for dependency(add the last version of FakerAndroid.jar to the lib folder)
            new Transfer(apk.getPath(), outDir, new TransformInvocation() {
                @Override
                public void callBack(String msg) {
                    progressIndicator.setText2(msg);
                }
            }).translate();
        }
        @Override
        public void onFinished() {
            super.onFinished();
            ProjectUtil.openOrImport(new File(outDir).getPath(),project,false);
            System.out.println("finished~~~");
        }
        @Override
        public void onThrowable(@NotNull Throwable error) {
            super.onThrowable(error);
        }
    }
    public OpenApkFileAction() {
    }

    public OpenApkFileAction(String text) {
        super(text);
    }

    private static class ProjectOnlyFileChooserDescriptor extends OpenProjectFileChooserDescriptorWithAsyncIcon {
        public ProjectOnlyFileChooserDescriptor() {
            setTitle(IdeBundle.message("title.open.project"));
        }
    }
    @Override
    public void update(AnActionEvent e) {
    }
    private static class ProjectOrFileChooserDescriptor extends OpenProjectFileChooserDescriptorWithAsyncIcon {
        private final FileChooserDescriptor myStandardDescriptor = FileChooserDescriptorFactory
                .createSingleFileNoJarsDescriptor().withHideIgnored(false);
        public ProjectOrFileChooserDescriptor() {
            setTitle(IdeBundle.message("title.open.file.or.project"));
        }

        @Override
        public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
            return file.isDirectory() ? super.isFileVisible(file, showHiddenFiles) : myStandardDescriptor.isFileVisible(file, showHiddenFiles);
        }
        @Override
        public boolean isFileSelectable(VirtualFile file) {
            return file.isDirectory() ? super.isFileSelectable(file) : myStandardDescriptor.isFileSelectable(file);
        }
        @Override
        public boolean isChooseMultiple() {
            return false;
        }
    }
}
