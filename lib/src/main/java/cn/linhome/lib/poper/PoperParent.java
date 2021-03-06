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
package cn.linhome.lib.poper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

final class PoperParent extends FrameLayout
{
    public PoperParent(Context context)
    {
        super(context);
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (getChildCount() > 1)
        {
            throw new RuntimeException("PoperParent can only add one child");
        }
    }

    @Override
    public void onViewRemoved(View child)
    {
        super.onViewRemoved(child);
        if (getChildCount() <= 0)
        {
            removeSelf();
        }
    }

    public void removeSelf()
    {
        if (((Activity) getContext()).isFinishing())
        {
            return;
        }

        final ViewParent parent = getParent();
        if (parent != null && (parent instanceof ViewGroup))
        {
            ((ViewGroup) parent).removeView(this);
        }
    }
}
