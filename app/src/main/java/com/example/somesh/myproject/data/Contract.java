package com.example.somesh.myproject.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class Contract {

    public static final String CONTENT_AUTHORITY = "com.example.somesh.myproject";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CATEGORIES = "category";
    public static final String PATH_QUESTIONS = "question";

    private Contract() {
    }

    public static final class CategoryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CATEGORIES);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        public final static String TABLE_NAME = "categories";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CATEGORY_NAME = "name";
    }

    public static final class QuestionsEntry implements BaseColumns {

        public static final Uri CONTENT_URI_Q = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_QUESTIONS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_QUESTIONS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_QUESTIONS;

        public final static String TABLE_NAME = "questions";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_Q_NO = "no";
        public final static String COLUMN_Q_Q = "question";
        public final static String COLUMN_Q_ANS = "answer";
        public final static String COLUMN_Q_OPTION1 = "option1";
        public final static String COLUMN_Q_OPTION2 = "option2";
        public final static String COLUMN_Q_OPTION3 = "option3";
        public final static String COLUMN_Q_OPTION4 = "option4";
    }

}
