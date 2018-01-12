package com.example.somesh.myproject.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Shiny Grace on 1/12/2018.
 */

public class CategoryContract {
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://com.example.somesh.myproject/");
    private static final String Path_Category = "EdtechApp";

    public static final class categoryListEntry implements BaseColumns {
        public static final Uri Category_Content_Uri = Uri.withAppendedPath(BASE_CONTENT_URI, Path_Category);
        public static final String TABLE_NAME = "CategoryList";
        public final static String _id = BaseColumns._ID;
        public static final String COLUMN_CATEGORY_NAME = "Categories";

    }

    public static final class categoryQuestions implements BaseColumns {
        public static final Uri Questions_Content_Uri = Uri.withAppendedPath(BASE_CONTENT_URI, Path_Category);
        public static final String TABLE_NAME = "Category_Questions";
        public static final String _id = BaseColumns._ID;
        public static final String COLUMN_CATEGORY_NAME = "Categories";
        public static final String COLUMN_QUESTIONS = "Questions";
        public static final String COLUMN_OPTIONA = "OptionA";
        public static final String COLUMN_OPTIONB = "OptionB";
        public static final String COLUMN_OPTIONC = "OptionC";
        public static final String COLUMN_OPTIOND = "OptionD";
        public static final String COLUMN_CORRECT_OPTION = "Answer";


    }

}
