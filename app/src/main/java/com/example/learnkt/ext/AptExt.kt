package com.example.learnkt.ext

import com.app.common_upload.inter.ISubBuilder
import com.example.learnkt.util.SoftMemorizers

val <F, T : ISubBuilder<F>> T.builder: F
    get() = this.subBuilder()!!

