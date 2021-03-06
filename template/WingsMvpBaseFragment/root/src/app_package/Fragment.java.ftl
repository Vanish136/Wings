package ${packageName};

import ${superClassFqcn};
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.annotation.Nullable;
import com.sources.javacode.R;
import com.lwkandroid.lib.common.mvp.MvpBaseFragment;
import com.lwkandroid.lib.core.annotation.ClickViews;
import com.lwkandroid.lib.core.annotation.ViewInjector;
/**
 * Description:View层
 * @author
 * @date
 */
public class ${uiClassName}Fragment extends MvpBaseFragment<${uiClassName}Presenter> implements ${uiClassName}Contract.IView<${uiClassName}Presenter> {

	/**
     * 创建该Fragment的静态方法
     */
    public static ${uiClassName}Fragment createInstance()
    {
        ${uiClassName}Fragment fragment = new ${uiClassName}Fragment();
        return fragment;
    }
	
	@Override
    protected ${uiClassName}Presenter createPresenter()
    {
        return new ${uiClassName}Presenter(this, new ${uiClassName}Model());
    }

	@Override
    protected void getArgumentsData(Bundle bundle, Bundle savedInstanceState)
    {

    }

    @Override
    protected int getContentViewId()
    {
		<#if generateLayout>
		return R.layout.${layoutName};
		<#else>	
		return 0;
		</#if>
    }
	
	@Override
    protected void initUI(View contentView)
    {
		ViewInjector.with(this);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState)
    {

    }

    @Override
	@ClickViews(values = {})
    public void onClick(int id, View view)
    {
		switch (id)
        {
            default:
                break;
        }
    }
	
}
