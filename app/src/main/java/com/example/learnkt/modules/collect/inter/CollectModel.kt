package com.example.learnkt.modules.collect.inter

import com.ciruy.heimerdinger.onion_database.database.StudentDatabase
import com.example.learnkt.inter.IModel

class CollectModel : IModel<CollectModel> {
    val studentdatabaseImpl = StudentDatabase.getDatabase()
}