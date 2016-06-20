package android.code.wing.baseapp.base;

import android.os.Bundle;

/**
 * 当一个activity包含多个fragmtnt或需跳转到其它fragment时使用这个基类更方便，否则请用{@link BaseSingleFragmentActivity}
 * 如不使用fragement请换{@link BaseFragmentActivity}
 * Created by wing on 16/1/24.
 */
public abstract class BaseMultFragmentActivity extends BaseFragmentActivity {

    private static final String KEY_INDEX = "key_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


/**===============================该部分用于避免重叠fragment的代码已停用，在BaseFragment使用了新的解决方案============================================*/
//    protected abstract int getCurrentIndex();
//@Override
//public void onSaveInstanceState(Bundle outState) {
//    super.onSaveInstanceState(outState);
//    // 保存当前Fragment的下标
//    outState.putInt(KEY_INDEX, getCurrentIndex());
//}
//    /**
//     * 非流程恢复所有，只显示<最后离开的页面>,如QQ下面几个tab
//     *
//     * @param savedInstanceState
//     * @param fragmentClasses    按<tab的顺序>，传入所有的tabFragment name
//     */
//    private void reStoreInstanceState4tabs(Bundle savedInstanceState, Class... fragmentClasses) {
//        if (fragmentClasses != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            List<Fragment> fragments = new ArrayList<>();
//            for (Class fragmentClass : fragmentClasses) {
//                fragments.add(fragmentManager.findFragmentByTag(fragmentClass.getName()));
//            }
//            //获取最后位置
//            int index = savedInstanceState.getInt(KEY_INDEX);
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            int count = fragments.size() - 1;
//            for (int i = 0; i < count; i++) {
//                if (index == i) {
//                    ft.show(fragments.get(i));
//                } else {
//                    ft.hide(fragments.get(i));
//                }
//            }
//            ft.commit();
//        }
//    }
//
//    /**
//     * 流程按顺序恢复所有，并只显示<最后一页>,如注册流程
//     *
//     * @param savedInstanceState
//     */
//    private void reStoreInstanceState4Flowing(Bundle savedInstanceState) {
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        if (fragments != null && fragments.size() > 0) {
//            boolean showFlag = false;
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            //倒过来恢复
//            int count = fragments.size() - 1;
//            for (int i = count; i >= 0; i--) {
//                Fragment fragment = fragments.get(i);
//                if (fragment != null) {
//                    if (!showFlag) {
//                        ft.show(fragments.get(i));
//                        showFlag = true;
//                    } else {
//                        ft.hide(fragments.get(i));
//                    }
//                }
//            }
//            ft.commit();
//        }
//    }
    /**===============================该部分代码已停用，在BaseFragment使用了新的解决方案============================================*/
}



