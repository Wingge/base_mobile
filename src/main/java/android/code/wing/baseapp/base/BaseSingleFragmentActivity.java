package android.code.wing.baseapp.base;

import android.code.wing.baseapp.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * 使用fragement的activity基类，如不使用fragement请换{@link BaseActivity}
 * 参考：http://blog.csdn.net/lmj623565791/article/details/42628537
 * Created by wing on 16/1/24.
 */
public abstract class BaseSingleFragmentActivity extends BaseActivity
{
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment =fm.findFragmentById(R.id.id_fragment_container);

        if(fragment == null )
        {
            fragment = createFragment() ;

            fm.beginTransaction().add(R.id.id_fragment_container,fragment).commit();
        }
    }

}
