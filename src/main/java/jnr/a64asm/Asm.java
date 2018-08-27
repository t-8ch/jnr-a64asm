/*
 * Copyright (C) 2018 ossdev07 ossdev@puresoftware.com
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package jnr.a64asm;

import static jnr.a64asm.REG.*;
import static jnr.a64asm.SIZE.*;

public final class Asm {
    private Asm() {}

    public static final CPU_A64 Aarch_64 = CPU_A64.A64;

    /** No register, can be used only in @c Mem operand. */
    public static final Register no_reg = new Register(NO_REG, 0);

    /** 64 bit General purpose register. */
    public static final Register x0 = Register.gpr(REG_X0);
    /** 64 bit General purpose register. */
    public static final Register x1 = Register.gpr(REG_X1);
    /** 64 bit General purpose register. */
    public static final Register x2 = Register.gpr(REG_X2);
    /** 64 bit General purpose register. */
    public static final Register x3 = Register.gpr(REG_X3);
    /** 64 bit General purpose register. */
    public static final Register x4 = Register.gpr(REG_X4);
    /** 64 bit General purpose register. */
    public static final Register x5 = Register.gpr(REG_X5);
    /** 64 bit General purpose register. */
    public static final Register x6 = Register.gpr(REG_X6);
    /** 64 bit General purpose register. */
    public static final Register x7 = Register.gpr(REG_X7);

    /** frame pointer */
    public static final Register fp = Register.gpr(REG_X29);
    /** link register */
    public static final Register lr = Register.gpr(REG_X30);
    /** stack pointer */
    public static final Register sp = Register.gpr(REG_X31);

    /** 64 bit 15 Spare General purpose register (64 bit mode only). */
    public static final Register w0 = Register.gpr(REG_W0);
    public static final Register w1 = Register.gpr(REG_W1);
    public static final Register w2 = Register.gpr(REG_W2);
    public static final Register w3 = Register.gpr(REG_W3);
    public static final Register w4 = Register.gpr(REG_W4);
    public static final Register w5 = Register.gpr(REG_W5);
    public static final Register w6 = Register.gpr(REG_W6);
    public static final Register w7 = Register.gpr(REG_W7);
    public static final Register w8 = Register.gpr(REG_W8);
    public static final Register w9 = Register.gpr(REG_W9);
    public static final Register w10 = Register.gpr(REG_W10);
    public static final Register w11 = Register.gpr(REG_W11);
    public static final Register w12 = Register.gpr(REG_W12);
    public static final Register w13 = Register.gpr(REG_W13);
    public static final Register w14 = Register.gpr(REG_W14);
    public static final Register w15 = Register.gpr(REG_W15);

    static final Mem _ptr_build(Label label, long disp, int ptrSize) {
        return new Mem(label, disp, ptrSize);
    }

    static final Mem _ptr_build(Label label, Register index, int shift, long disp, int ptrSize) {
        return new Mem(label, index, shift, disp, ptrSize);
    }

    /** Absolute addressing */
    static final Mem _ptr_build_abs(long target, long disp, int ptrSize) {
        return new Mem(target, disp, ptrSize);
    }

    static final Mem _ptr_build_abs(long target, Register index, int shift, long disp, int ptrSize) {
        return new Mem(target, index, shift, disp, ptrSize);
    }

    static final Mem _ptr_build(Register base, long disp, int ptrSize) {
      return new Mem(base, disp, ptrSize);
    }

    static final Mem _ptr_build(Register base, Register index, int shift, long disp, int ptrSize) {
      return new Mem(base, index, shift, disp, ptrSize);
    }

    // ============================================================================
    // [AsmJit::Mem - ptr[displacement]]
    // ============================================================================

    /** Create pointer operand with not specified size. */
    public static final Mem ptr(Label label, long disp) {
        return _ptr_build(label, disp, 0);
    }

    /** Create pointer operand with not specified size. */
    public static final Mem ptr(Label label) {
        return _ptr_build(label, 0, 0);
    }

    /** Create word (2 Bytes) pointer operand. */
    public static final Mem word_ptr(Label label, long disp) {
        return _ptr_build(label, disp, SIZE_WORD);
    }

    /** Create word (2 Bytes) pointer operand. */
    public static final Mem word_ptr(Label label) {
        return _ptr_build(label, 0, SIZE_WORD);
    }

    /** Create dword (4 Bytes) pointer operand. */
    public static final Mem dword_ptr(Label label, long disp) {
        return _ptr_build(label, disp, SIZE_DWORD);
    }

    /** Create dword (4 Bytes) pointer operand. */
    public static final Mem dword_ptr(Label label) {
        return _ptr_build(label, 0, SIZE_DWORD);
    }

    /** Create pointer operand with not specified size. */
    public static final Mem ptr(Label label, Register index, int shift, long disp) {
        return _ptr_build(label, index, shift, disp, 0);
    }

    /** Create dword (4 Bytes) pointer operand. */
    public static final Mem word_ptr(Label label, Register index, int shift, long disp) {
        return _ptr_build(label, index, shift, disp, SIZE_WORD);
    }

    /** Create qword (8 Bytes) pointer operand. */
    public static final Mem dword_ptr(Label label, Register index, int shift, long disp) {
        return _ptr_build(label, index, shift, disp, SIZE_DWORD);
    }

    public static final Mem word_ptr_abs(long target, Register index, int shift, long disp) {
        return _ptr_build_abs(target, index, shift, disp, SIZE_WORD);
    }

    public static final Mem dword_ptr_abs(long target, Register index, int shift, long disp) {
        return _ptr_build_abs(target, index, shift, disp, SIZE_DWORD);
    }

// ============================================================================
// [AsmJit::Mem - ptr[base + displacement]]
// ============================================================================
    /** Create pointer operand with not specified size. */
    public static final Mem ptr(Register base, long disp) {
        return _ptr_build(base, disp, 0);
    }

    /** Create word (4 Bytes) pointer operand. */
    public static final Mem word_ptr(Register base, long disp) {
        return _ptr_build(base, disp, SIZE_WORD);
    }

    /** Create dword (8 Bytes) pointer operand. */
    public static final Mem dword_ptr(Register base, long disp) {
        return _ptr_build(base, disp, SIZE_DWORD);
    }

    // ============================================================================
    // [AsmJit::Mem - ptr[base + (index << shift) + displacement]]
    // ============================================================================

    /** Create pointer operand with not specified size. */
    public static final Mem ptr(Register base, Register index, int shift, long disp) {
        return _ptr_build(base, index, shift, disp, 0);
    }

    /** Create word (2 Bytes) pointer operand. */
    public static final Mem word_ptr(Register base, Register index, int shift, long disp) {
        return _ptr_build(base, index, shift, disp, SIZE_WORD);
    }

    /** Create dword (4 Bytes) pointer operand. */
    public static final Mem dword_ptr(Register base, Register index, int shift, long disp) {
        return _ptr_build(base, index, shift, disp, SIZE_DWORD);
    }

    public static final Immediate imm(long value) {
         return Immediate.imm(value);
    }

    public static final Immediate uimm(long value) {
        return Immediate.imm(value);
    }
}
