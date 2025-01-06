/*
 * Copyright (c) 2000, 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.misc;


import java.lang.reflect.Field;

public final class Unsafe {

    private static final Unsafe theUnsaf = null;

    public int getInt(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putInt(Object o, long offset, int x) {
        throw new UnsupportedOperationException("dummy");
    }

    public Object getObject(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putObject(Object o, long offset, Object x) {
        throw new UnsupportedOperationException("dummy");
    }

    public boolean getBoolean(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putBoolean(Object o, long offset, boolean x) {
        throw new UnsupportedOperationException("dummy");
    }

    public byte getByte(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putByte(Object o, long offset, byte x) {
        throw new UnsupportedOperationException("dummy");
    }

    public short getShort(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putShort(Object o, long offset, short x) {
        throw new UnsupportedOperationException("dummy");
    }

    public char getChar(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putChar(Object o, long offset, char x) {
        throw new UnsupportedOperationException("dummy");
    }

    public long getLong(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putLong(Object o, long offset, long x) {
        throw new UnsupportedOperationException("dummy");
    }

    public float getFloat(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putFloat(Object o, long offset, float x) {
        throw new UnsupportedOperationException("dummy");
    }

    public double getDouble(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putDouble(Object o, long offset, double x) {
        throw new UnsupportedOperationException("dummy");
    }

    public byte getByte(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putByte(long address, byte x) {
        throw new UnsupportedOperationException("dummy");
    }

    public short getShort(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putShort(long address, short x) {
        throw new UnsupportedOperationException("dummy");
    }

    public char getChar(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putChar(long address, char x) {
        throw new UnsupportedOperationException("dummy");
    }

    public int getInt(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putInt(long address, int x) {
        throw new UnsupportedOperationException("dummy");
    }

    public long getLong(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putLong(long address, long x) {
        throw new UnsupportedOperationException("dummy");
    }

    public float getFloat(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putFloat(long address, float x) {
        throw new UnsupportedOperationException("dummy");
    }

    public double getDouble(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putDouble(long address, double x) {
        throw new UnsupportedOperationException("dummy");
    }

    public long getAddress(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putAddress(long address, long x) {
        throw new UnsupportedOperationException("dummy");
    }

    public long allocateMemory(long bytes) {
        throw new UnsupportedOperationException("dummy");
    }

    public long reallocateMemory(long address, long bytes) {
        throw new UnsupportedOperationException("dummy");
    }

    public void setMemory(Object o, long offset, long bytes, byte value) {
        throw new UnsupportedOperationException("dummy");
    }

    public void setMemory(long address, long bytes, byte value) {
        throw new UnsupportedOperationException("dummy");
    }

    public void copyMemory(Object srcBase, long srcOffset,
                           Object destBase, long destOffset,
                           long bytes) {
        throw new UnsupportedOperationException("dummy");
    }

    public void copyMemory(long srcAddress, long destAddress, long bytes) {
        throw new UnsupportedOperationException("dummy");
    }

    public void freeMemory(long address) {
        throw new UnsupportedOperationException("dummy");
    }

    public static final int INVALID_FIELD_OFFSET = Integer.valueOf(0);

    public long objectFieldOffset(Field f) {
        throw new UnsupportedOperationException("dummy");
    }

    public long staticFieldOffset(Field f) {
        throw new UnsupportedOperationException("dummy");
    }

    public Object staticFieldBase(Field f) {
        throw new UnsupportedOperationException("dummy");
    }

    public boolean shouldBeInitialized(Class<?> c) {
        throw new UnsupportedOperationException("dummy");
    }

    public void ensureClassInitialized(Class<?> c) {
        throw new UnsupportedOperationException("dummy");
    }

    public int arrayBaseOffset(Class<?> arrayClass) {
        throw new UnsupportedOperationException("dummy");
    }

    public static final int ARRAY_BOOLEAN_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_BYTE_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_SHORT_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_CHAR_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_INT_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_LONG_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_FLOAT_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_DOUBLE_BASE_OFFSET = Integer.valueOf(0);

    public static final int ARRAY_OBJECT_BASE_OFFSET = Integer.valueOf(0);

    public int arrayIndexScale(Class<?> arrayClass) {
        throw new UnsupportedOperationException("dummy");
    }

    public static final int ARRAY_BOOLEAN_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_BYTE_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_SHORT_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_CHAR_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_INT_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_LONG_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_FLOAT_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_DOUBLE_INDEX_SCALE = Integer.valueOf(0);

    public static final int ARRAY_OBJECT_INDEX_SCALE = Integer.valueOf(0);

    public int addressSize() {
        throw new UnsupportedOperationException("dummy");
    }

    public static final int ADDRESS_SIZE = Integer.valueOf(0);

    public int pageSize() {
        throw new UnsupportedOperationException("dummy");
    }


    public Object allocateInstance(Class<?> cls)
        throws InstantiationException {
        throw new UnsupportedOperationException("dummy");
    }

    public void throwException(Throwable ee) {
        throw new UnsupportedOperationException("dummy");
    }

    public boolean compareAndSwapObject(Object o, long offset,
                                              Object expected,
                                              Object x) {
        throw new UnsupportedOperationException("dummy");
    }

    public boolean compareAndSwapInt(Object o, long offset,
                                           int expected,
                                           int x) {
        throw new UnsupportedOperationException("dummy");
    }

    public boolean compareAndSwapLong(Object o, long offset,
                                            long expected,
                                            long x) {
        throw new UnsupportedOperationException("dummy");
    }

    public Object getObjectVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putObjectVolatile(Object o, long offset, Object x) {
        throw new UnsupportedOperationException("dummy");
    }

    public int getIntVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putIntVolatile(Object o, long offset, int x) {
        throw new UnsupportedOperationException("dummy");
    }

    public boolean getBooleanVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putBooleanVolatile(Object o, long offset, boolean x) {
        throw new UnsupportedOperationException("dummy");
    }

    public byte getByteVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putByteVolatile(Object o, long offset, byte x) {
        throw new UnsupportedOperationException("dummy");
    }

    public short getShortVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putShortVolatile(Object o, long offset, short x) {
        throw new UnsupportedOperationException("dummy");
    }

    public char getCharVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putCharVolatile(Object o, long offset, char x) {
        throw new UnsupportedOperationException("dummy");
    }

    public long getLongVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putLongVolatile(Object o, long offset, long x) {
        throw new UnsupportedOperationException("dummy");
    }

    public float getFloatVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putFloatVolatile(Object o, long offset, float x) {
        throw new UnsupportedOperationException("dummy");
    }

    public double getDoubleVolatile(Object o, long offset) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putDoubleVolatile(Object o, long offset, double x) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putOrderedObject(Object o, long offset, Object x) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putOrderedInt(Object o, long offset, int x) {
        throw new UnsupportedOperationException("dummy");
    }

    public void putOrderedLong(Object o, long offset, long x) {
        throw new UnsupportedOperationException("dummy");
    }

    public void unpark(Object thread) {
        throw new UnsupportedOperationException("dummy");
    }

    public void park(boolean isAbsolute, long time) {
        throw new UnsupportedOperationException("dummy");
    }

    public int getLoadAverage(double[] loadavg, int nelems) {
        throw new UnsupportedOperationException("dummy");
    }

    public int getAndAddInt(Object o, long offset, int delta) {
        throw new UnsupportedOperationException("dummy");
    }

    public long getAndAddLong(Object o, long offset, long delta) {
        throw new UnsupportedOperationException("dummy");
    }

    public int getAndSetInt(Object o, long offset, int newValue) {
        throw new UnsupportedOperationException("dummy");
    }

    public long getAndSetLong(Object o, long offset, long newValue) {
        throw new UnsupportedOperationException("dummy");
    }

    public Object getAndSetObject(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException("dummy");
    }

    public void loadFence() {
        throw new UnsupportedOperationException("dummy");
    }

    public void storeFence() {
        throw new UnsupportedOperationException("dummy");
    }

    public void fullFence() {
        throw new UnsupportedOperationException("dummy");
    }

    public void invokeCleaner(java.nio.ByteBuffer directBuffer) {
        throw new UnsupportedOperationException("dummy");
    }
}
