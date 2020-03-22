package net.riking.auto.commmon.handle;

import net.riking.auto.commmon.enums.FileType;


import java.util.ArrayList;
import java.util.List;

public class FileHandleContext {

    static List<FileHandle> list = new ArrayList<>();

    static {
     /*   list.add(new TxtDelimiterFileHandle(FileType.TXT_DELIMITER));
        list.add(new TxtFixedFileHandle(FileType.TXT_FIXED));*/
    }

    public static FileHandle getFileHandle(FileType fileType) {
       /*  for (FileHandle x : list) {
           if (x.fileType.equals(fileType)) {
                return x;
            }
        }*/
        return null;
    }

}
