package com.igorwojda.traktclient.suite

import com.igorwojda.traktclient.ApplicationTest
import com.igorwojda.traktclient.MovieTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(ApplicationTest::class, MovieTest::class)
class InstrumentationTestSuite

