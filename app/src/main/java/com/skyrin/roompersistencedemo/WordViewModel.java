package com.skyrin.roompersistencedemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.skyrin.roompersistencedemo.data.Word;
import com.skyrin.roompersistencedemo.data.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    // 为所有单词添加“getter”方法。这完全隐藏了UI的实现。
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // 创建一个调用Repository的insert（）方法的包装器insert（）方法。通过这种方式，insert（）的实现完全隐藏在UI之外。
    public void insert(Word word){
        mRepository.insert(word);
    }

    /**
     * 警告：永远不要将上下文传递给ViewModel实例。不要在ViewModel中存储Activity，Fragment或View实例或其Context。
     *
     * 例如，在设备旋转时，可以在ViewModel的生命周期中多次销毁和创建Activity。
     * 如果在ViewModel中存储对Activity的引用，则最终会得到指向已销毁Activity的引用。这是内存泄漏。
     *
     * 如果您需要应用程序上下文，请使用AndroidViewModel，如此本例中所示。
     * */

    /**
     * 重要说明：ViewModel不是onSaveInstanceState（）方法的替代品，因为ViewModel不会在进程关闭后继续存在。
     * */
}
