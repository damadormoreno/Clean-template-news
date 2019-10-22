package com.deneb.newsapp

import android.app.Application
import android.content.Context
import com.deneb.newsapp.core.platform.BaseActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest=Config.NONE,
    application = AndroidTest.ApplicationStub::class,
    sdk = [21]
)
abstract class AndroidTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField val injectMocks = InjectMocksRule.create(this@AndroidTest)

    fun context(): Context = RuntimeEnvironment.application

    fun activityContext(): Context = mock(BaseActivity::class.java)

    internal class ApplicationStub : Application()
}
