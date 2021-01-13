package com.ciruy.heimerdinger.onion_kapt

import ciruy.b.heimerdinger.annotation.BuilderClass

@BuilderClass(classArray = [Logger::class,OnionMainProcessor::class])
interface OnionBuilderClass {
}