package com.glinyanov.childstars.features.aboutchilds.navigation

import com.glinyanov.childstars.features.aboutchilds.domain.ChildDo

interface AboutChildsFeatureNavigationDelegate {
    fun onChildClick(child: ChildDo)
    fun onNeedAuthAction()
}