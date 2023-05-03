/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        // recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = LetterAdapter()

        // Sets the LinearLayoutManager of the recylerview
        chooseLayout()


    }

    private fun chooseLayout() {
        if(isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if(menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //      menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)

        menuItem.icon =
            if(isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)

        // menuItem.icon = ContextCompat.getDrawable(this, if(isLinearLayoutManager) {R.drawable.ic_grid_layout} else {R.drawable.ic_linear_layout} )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)  //메뉴 객체 생성
        //inflate : xml 레이아웃 파일을 코드에서 사용할 수 있는 뷰 객체 계층 구조로 확장하는 과정을 말함
        // 즉, 메뉴 xml 파일 (layout_menu.xml)의 레이아웃을 가져와서 메뉴를 구성하는 뷰 객체 계층 구조를 생성하는 역할
        // 이렇게 생성된 뷰 객체 계층 구조는 코드에서 직접적으로 조작 가능
        // onCreateOptionMenu 메소드에서 주로 사용되는 거. 메뉴를 생성할 때 사용됨

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)

        return when(item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite(상반되는 반대되는) value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            // Otherwise, do nothing and use the core event handling (핵심 이벤트 처리)

            // when clauses(when절) require that all possible paths be accounted for explicitly, (명시적인)
            //   for instance both the true and false cases if the value is a Boolean,
            //   or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }

    }


}

/**
 * 옵션 메뉴를 구현하여
 * 사용자가 목록 및 그리드 레이아웃 간에 전환할 수 있는 버튼을 표시
 *
 */