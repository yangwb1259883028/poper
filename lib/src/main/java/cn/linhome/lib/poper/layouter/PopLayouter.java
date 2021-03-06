/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
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
package cn.linhome.lib.poper.layouter;

import android.view.View;

import cn.linhome.lib.poper.FPoper;

/**
 * popview绘制接口
 */
public interface PopLayouter
{
    /**
     * 刷新popview的位置
     *
     * @param popView
     * @param popViewParent popview父布局
     * @param poper
     */
    void layout(View popView, View popViewParent, FPoper poper);
}
