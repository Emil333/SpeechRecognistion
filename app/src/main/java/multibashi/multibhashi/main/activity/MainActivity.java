package multibashi.multibhashi.main.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import multibashi.multibhashi.CustomViewPager;
import multibashi.multibhashi.R;
import multibashi.multibhashi.main.LessonPagerAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.MainContractView, View.OnClickListener {

    @BindView(R.id.view_pager)
    CustomViewPager mViewPager;
    @BindView(R.id.next_button)
    AppCompatImageView nextButton;

    private MainPresenterImpl presenter;
    private LessonPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        nextButton.setOnClickListener(this);
        presenter = new MainPresenterImpl(this);
        presenter.requestData();
    }

    @Override
    public void setAdapter(ArrayList<Fragment> fragmentList) {

        mAdapter = new LessonPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setPagingEnabled(false);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void naviagetToNextLesson(int nextPage) {
        mViewPager.setCurrentItem(nextPage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_button:
                presenter.setNextPage();
                break;
        }
    }
}
