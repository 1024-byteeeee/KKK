/*
 * This file is part of the KKK project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025 1024_byteeeee and contributors
 *
 * KKK is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KKK is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with KKK. If not, see <https://www.gnu.org/licenses/>.
 */

package top.byteeeee.fuzz.utils.MessageTextEventUtils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.item.ItemStack;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class HoverEventUtil {
    public static final HoverEvent.Action SHOW_TEXT = HoverEvent.Action.SHOW_TEXT;
    public static final HoverEvent.Action SHOW_ITEM = HoverEvent.Action.SHOW_ITEM;
    public static final HoverEvent.Action SHOW_ENTITY = HoverEvent.Action.SHOW_ENTITY;
    private static final Map<HoverEvent.Action, Function<Object, HoverEvent>> HOVER_EVENT_ACTION_MAP = new HashMap<>();

    public static HoverEvent event(HoverEvent.Action action, Object value) {
        return Optional.ofNullable(HOVER_EVENT_ACTION_MAP.get(action)).map(function -> function.apply(value)).orElseThrow(() -> new IllegalArgumentException("Invalid action or value type"));
    }

    static {
        HOVER_EVENT_ACTION_MAP.put(SHOW_TEXT, value -> new HoverEvent.ShowText((Text) value));
        HOVER_EVENT_ACTION_MAP.put(SHOW_ITEM, value -> new HoverEvent.ShowItem((ItemStack) value));
        HOVER_EVENT_ACTION_MAP.put(SHOW_ENTITY, value -> new HoverEvent.ShowEntity((HoverEvent.EntityContent) value));
    }
}
