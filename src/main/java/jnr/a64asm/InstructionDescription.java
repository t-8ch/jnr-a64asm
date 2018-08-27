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

import java.util.EnumMap;
import java.util.Map;
import static jnr.a64asm.INST_CODE.*;
import static jnr.a64asm.InstructionGroup.*;

public final class InstructionDescription {
    final INST_CODE code;
    final int opcode;
    final int mask;
    final InstructionGroup group;

    private static final Map<INST_CODE, InstructionDescription> table
            = new EnumMap<INST_CODE, InstructionDescription>(INST_CODE.class);

    private static final InstructionDescription MAKE_INST(INST_CODE code, int opcode, int mask, InstructionGroup group){
        InstructionDescription id = new InstructionDescription(code, opcode, mask, group);
        table.put(code, id);
        return id;
    }

    InstructionDescription(INST_CODE code, int opCode, int mask,InstructionGroup group) {
        this.code = code;
        this.opcode = opCode;
        this.mask = mask;
        this.group = group;
    }

    public static final InstructionDescription find(INST_CODE code) {

        InstructionDescription id = table.get(code);
        if (id == null) {
            throw new IllegalArgumentException("no description for " + code);
        }

        return id;
    }

    private static final InstructionDescription[] all = {
        // Instruction code (enum)      | instruction name   | group           | operator 1 flags| operator 2 flags| r| opCode1   | opcode2
        MAKE_INST(INST_ADC_ADDSUB_CARRY, 0x1a000000, 0x7fe0fc00, addsub_carry),
        MAKE_INST(INST_ADCS_ADDSUB_CARRY, 0x3a000000, 0x7fe0fc00, addsub_carry),
        MAKE_INST(INST_ADD_ADDSUB_IMM, 0x11000000, 0x7f000000, addsub_imm),
        MAKE_INST(INST_ADD_ADDSUB_SHIFT, 0xb000000, 0x7f200000, addsub_shift),
        MAKE_INST(INST_ADDS_ADDSUB_IMM, 0x31000000, 0x7f000000, addsub_imm),
        MAKE_INST(INST_ADD_EXT_ADDSUB_EXT, 0x0b200000, 0x7fe00000, addsub_ext),
        MAKE_INST(INST_ADDS_ADDSUB_EXT, 0x2b200000, 0x7fe00000, addsub_ext),
        MAKE_INST(INST_ADDS_ADDSUB_SHIFT, 0x2b000000, 0x7f200000, addsub_shift),
        MAKE_INST(INST_ADR_PCRELADDR, 0x10000000, 0x9f000000, pcreladdr),
        MAKE_INST(INST_ADRP_PCRELADDR, 0x90000000, 0x9f000000, pcreladdr),
        MAKE_INST(INST_AND_LOG_IMM, 0x12000000, 0x7f800000, log_imm),
        MAKE_INST(INST_AND_LOG_SHIFT, 0xa000000, 0x7f200000, log_shift),
        MAKE_INST(INST_ANDS_LOG_IMM, 0x72000000, 0x7f800000, log_imm),
        MAKE_INST(INST_ANDS_LOG_SHIFT, 0x6a000000, 0x7f200000, log_shift),
        MAKE_INST(INST_ASR_BITFIELD, 0x13000000, 0x7f800000, bitfield),
        MAKE_INST(INST_ASR_DP_2SRC, 0x1ac02800, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_ASRV_DP_2SRC, 0x1ac02800, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_AT_IC_SYSTEM, 0xd5080000, 0xfff80000, ic_system),
        MAKE_INST(INST_B_C_CONDBRANCH, 0x14000000, 0xfc000000, condbranch),
        MAKE_INST(INST_B_BRANCH_IMM, 0x54000000, 0xff000010, branch_imm),
        MAKE_INST(INST_BCC_CONDBRANCH, 0x54000003, 0xff00001f, condbranch),
        MAKE_INST(INST_BCS_CONDBRANCH, 0x54000002, 0xff00001f, condbranch),
        MAKE_INST(INST_BEQ_CONDBRANCH, 0x54000000, 0xff00001f, condbranch),
        MAKE_INST(INST_BFI_BITFIELD, 0x33000000, 0x7f800000, bitfield),
        MAKE_INST(INST_BFM_BITFIELD, 0x33000000, 0x7f800000, bitfield),
        MAKE_INST(INST_BFXIL_BITFIELD, 0x33000000, 0x7f800000, bitfield),
        MAKE_INST(INST_BGE_CONDBRANCH, 0x5400000a, 0xff00001f, condbranch),
        MAKE_INST(INST_BGT_CONDBRANCH, 0x5400000c, 0xff00001f, condbranch),
        MAKE_INST(INST_BHI_CONDBRANCH, 0x54000008, 0xff00001f, condbranch),
        MAKE_INST(INST_BHS_CONDBRANCH, 0x54000002, 0xff00001f, condbranch),
        MAKE_INST(INST_BIC_LOG_IMM, 0x12000000, 0x7f800000, log_imm),
        MAKE_INST(INST_BIC_LOG_SHIFT, 0xa200000, 0x7f200000, log_shift),
        MAKE_INST(INST_BICS_LOG_SHIFT, 0x6a200000, 0x7f200000, log_shift),
        MAKE_INST(INST_BL_BRANCH_IMM, 0x94000000, 0xfc000000, branch_imm),
        MAKE_INST(INST_BLE_CONDBRANCH, 0x5400000d, 0xff00001f, condbranch),
        MAKE_INST(INST_BLO_CONDBRANCH, 0x54000003, 0xff00001f, condbranch),
        MAKE_INST(INST_BLR_BRANCH_REG, 0xd63f0000, 0xfffffc1f, branch_reg),
        MAKE_INST(INST_BLS_CONDBRANCH, 0x54000009, 0xff00001f, condbranch),
        MAKE_INST(INST_BLT_CONDBRANCH, 0x5400000b, 0xff00001f, condbranch),
        MAKE_INST(INST_BMI_CONDBRANCH, 0x54000004, 0xff00001f, condbranch),
        MAKE_INST(INST_BNE_CONDBRANCH, 0x54000001, 0xff00001f, condbranch),
        MAKE_INST(INST_BPL_CONDBRANCH, 0x54000005, 0xff00001f, condbranch),
        MAKE_INST(INST_BR_BRANCH_REG, 0xd61f0000, 0xfffffc1f, branch_reg),
        MAKE_INST(INST_BRK_EXCEPTION, 0xd4200000, 0xffe0001f, exception),
        MAKE_INST(INST_BVC_CONDBRANCH, 0x54000007, 0xff00001f, condbranch),
        MAKE_INST(INST_BVS_CONDBRANCH, 0x54000006, 0xff00001f, condbranch),
        MAKE_INST(INST_CBNZ_COMPBRANCH, 0x35000000, 0x7f000000, compbranch),
        MAKE_INST(INST_CBZ_COMPBRANCH, 0x34000000, 0x7f000000, compbranch),
        MAKE_INST(INST_CCMN_CONDCMP_IMM, 0x3a400800, 0x7fe00c10, condcmp_imm),
        MAKE_INST(INST_CCMN_CONDCMP_REG, 0x3a400000, 0x7fe00c10, condcmp_reg),
        MAKE_INST(INST_CCMP_CONDCMP_IMM, 0x7a400800, 0x7fe00c10, condcmp_imm),
        MAKE_INST(INST_CCMP_CONDCMP_REG, 0x7a400000, 0x7fe00c10, condcmp_reg),
        MAKE_INST(INST_CINC_CONDSEL, 0x1a800400, 0x7fe00c00, condsel),
        MAKE_INST(INST_CINV_CONDSEL, 0x5a800000, 0x7fe00c00, condsel),
        MAKE_INST(INST_CLREX_IC_SYSTEM, 0xd503305f, 0xfffff0ff, ic_system),
        MAKE_INST(INST_CLS_DP_1SRC, 0x5ac01400, 0x7ffffc00, dp_1src),
        MAKE_INST(INST_CLZ_DP_1SRC, 0x5ac01000, 0x7ffffc00, dp_1src),
        MAKE_INST(INST_CMN_ADDSUB_EXT, 0x2b20001f, 0x7fe0001f, addsub_ext),
        MAKE_INST(INST_CMN_ADDSUB_IMM, 0x3100001f, 0x7f00001f, addsub_imm),
        MAKE_INST(INST_CMN_ADDSUB_SHIFT, 0x2b00001f, 0x7f20001f, addsub_shift),
        MAKE_INST(INST_CMP_ADDSUB_EXT, 0x6b20001f, 0x7fe0001f, addsub_ext),
        MAKE_INST(INST_CMP_ADDSUB_IMM, 0x7100001f, 0x7f00001f, addsub_imm),
        MAKE_INST(INST_CMP_ADDSUB_SHIFT, 0x6b00001f, 0x7f20001f, addsub_shift),
        MAKE_INST(INST_CNEG_CONDSEL, 0x5a800400, 0x7fe00c00, condsel),
        MAKE_INST(INST_CSEL_CONDSEL, 0x1a800000, 0x7fe00c00, condsel),
        MAKE_INST(INST_CSET_CONDSEL, 0x1a9f07e0, 0x7fff0fe0, condsel),
        MAKE_INST(INST_CSETM_CONDSEL, 0x5a9f03e0, 0x7fff0fe0, condsel),
        MAKE_INST(INST_CSINC_CONDSEL, 0x1a800400, 0x7fe00c00, condsel),
        MAKE_INST(INST_CSINV_CONDSEL, 0x5a800000, 0x7fe00c00, condsel),
        MAKE_INST(INST_CSNEG_CONDSEL, 0x5a800400, 0x7fe00c00, condsel),
        MAKE_INST(INST_DC_IC_SYSTEM, 0xd5080000, 0xfff80000, ic_system),
        MAKE_INST(INST_DCPS1_EXCEPTION, 0xd4a00001, 0xffe0001f, exception),
        MAKE_INST(INST_DCPS2_EXCEPTION, 0xd4a00002, 0xffe0001f, exception),
        MAKE_INST(INST_DCPS3_EXCEPTION, 0xd4a00003, 0xffe0001f, exception),
        MAKE_INST(INST_DMB_IC_SYSTEM, 0xd50330bf, 0xfffff0ff, ic_system),
        MAKE_INST(INST_DRPS_BRANCH_REG, 0xd6bf03e0, 0xffffffff, branch_reg),
        MAKE_INST(INST_DSB_IC_SYSTEM, 0xd503309f, 0xfffff0ff, ic_system),
        MAKE_INST(INST_EON_LOG_SHIFT, 0x4a200000, 0x7f200000, log_shift),
        MAKE_INST(INST_EOR_LOG_IMM, 0x52000000, 0x7f800000, log_imm),
        MAKE_INST(INST_EOR_LOG_SHIFT, 0x4a000000, 0x7f200000, log_shift),
        MAKE_INST(INST_ERET_BRANCH_REG, 0xd69f03e0, 0xffffffff, branch_reg),
        MAKE_INST(INST_EXTR_EXTRACT, 0x13800000, 0x7fa00000, extract),
        MAKE_INST(INST_HINT_IC_SYSTEM, 0xd503201f, 0xfffff01f, ic_system),
        MAKE_INST(INST_HLT_EXCEPTION, 0xd4400000, 0xffe0001f, exception),
        MAKE_INST(INST_HVC_EXCEPTION, 0xd4000002, 0xffe0001f, exception),
        MAKE_INST(INST_IC_IC_SYSTEM, 0xd5080000, 0xfff80000, ic_system),
        MAKE_INST(INST_ISB_IC_SYSTEM, 0xd50330df, 0xfffff0ff, ic_system),
        MAKE_INST(INST_LDAR_LDSTEXCL, 0x88dffc00, 0xbfe08000, ldstexcl),
        MAKE_INST(INST_LDARB_LDSTEXCL, 0x8dffc00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_LDARH_LDSTEXCL, 0x48dffc00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_LDAXP_LDSTEXCL, 0x887f8000, 0xbfe08000, ldstexcl_op3),
        MAKE_INST(INST_LDAXR_LDSTEXCL, 0x885ffc00, 0xbfe08000, ldstexcl),
        MAKE_INST(INST_LDAXRB_LDSTEXCL, 0x85ffc00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_LDAXRH_LDSTEXCL, 0x485ffc00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_LDNP_LDSTNAPAIR_OFFS, 0x28400000, 0x7fc00000, ldstnapair_offs),
        MAKE_INST(INST_LDNP_LDSTNAPAIR_OFFS_V, 0x2c400000, 0x3fc00000, ldstnapair_offs),
        MAKE_INST(INST_LDP_POST_INDEXED_IDST_IMM9, 0x28c00000, 0x7ec00000, ldst_imm9_2reg),
        MAKE_INST(INST_LDP_PRE_INDEXED_IDST_IMM9, 0x29c00000, 0x7ec00000, ldst_imm9_2reg),
        MAKE_INST(INST_LDP_LDSTPAIR_OFF_LDST_POS, 0x29400000, 0x7ec00000, ldst_pos_2reg),
        MAKE_INST(INST_LDPSW_POST_INDEXED, 0x68c00000, 0xffc00000, ldstpair_indexed),
        MAKE_INST(INST_LDPSW_PRE_INDEXED, 0x69c00000, 0xffc00000, ldstpair_indexed),
        MAKE_INST(INST_LDPSW_OFF, 0x69400000, 0xffc00000, ldstpair_off),
        MAKE_INST(INST_LDR_IMM_POST, 0xb8400400, 0xbfe00400, ldst_imm9),
        MAKE_INST(INST_LDR_IMM_PRE,  0xb8400C00, 0xbfe00c00, ldst_imm9),
        MAKE_INST(INST_LDR_IMM_OFF,  0xb9400000, 0xbfc00000, ldst_pos),
        MAKE_INST(INST_LDR_REG,      0xb8600800, 0xbfe00c00,ldst_regoff),
        MAKE_INST(INST_LDR_LOADLIT,  0x18000000, 0xbf000000, loadlit),
        MAKE_INST(INST_LDRB_IMM_POST, 0x38400400, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_LDRB_IMM_PRE,  0x38400c00, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_LDRB_IMM_OFF,  0x39400000, 0xffc00000, ldst_pos),
        MAKE_INST(INST_LDRB_REG,      0x38600800, 0xffe00c00, ldst_regoff),
        MAKE_INST(INST_LDRH_IMM_POST, 0x78400400, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_LDRH_IMM_PRE,  0x78400c00, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_LDRH_IMM_OFF,  0x79400000, 0xffc00000, ldst_pos),
        MAKE_INST(INST_LDRH_REG,      0x78600800, 0xffe00c00, ldst_regoff),
        MAKE_INST(INST_LDRSB_IMM_POST, 0x38800400, 0xffa00c00, ldst_imm9),
        MAKE_INST(INST_LDRSB_IMM_PRE,  0x38800c00, 0xffa00c00, ldst_imm9),
        MAKE_INST(INST_LDRSB_IMM_OFF,  0x39800000, 0xff800000, ldst_pos),
        MAKE_INST(INST_LDRSB_REG,      0x38a00800, 0xffa00c00, ldst_regoff),
        MAKE_INST(INST_LDRSH_IMM_POST, 0x78800400, 0xffa00c00, ldst_imm9),
        MAKE_INST(INST_LDRSH_IMM_PRE,  0x78800c00, 0xffa00c00, ldst_imm9),
        MAKE_INST(INST_LDRSH_IMM_OFF,  0x79800000, 0xff800000, ldst_pos),
        MAKE_INST(INST_LDRSH_REG,      0x78a00800, 0xffa00c00, ldst_regoff),
        MAKE_INST(INST_LDRSW_IMM_POST, 0xb8800400, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_LDRSW_IMM_PRE,  0xb8800C00, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_LDRSW_IMM_OFF,  0xb9800000, 0xffc00000, ldst_pos),
        MAKE_INST(INST_LDRSW_REG,      0xb8a00800, 0xffe00c00, ldst_regoff),
        MAKE_INST(INST_LDRSW_LOADLIT,  0x98000000, 0xff000000, loadlit),
        MAKE_INST(INST_LDTR_LDST_UNPRIV, 0xb8400800, 0xbfe00c00, ldst_unpriv),
        MAKE_INST(INST_LDTRB_LDST_UNPRIV, 0x38400800, 0xffe00c00, ldst_unpriv),
        MAKE_INST(INST_LDTRH_LDST_UNPRIV, 0x78400800, 0xffe00c00, ldst_unpriv),
        MAKE_INST(INST_LDTRSB_LDST_UNPRIV, 0x38800800, 0xffa00c00, ldst_unpriv),
        MAKE_INST(INST_LDTRSH_LDST_UNPRIV, 0x78800800, 0xffa00c00, ldst_unpriv),
        MAKE_INST(INST_LDTRSW_LDST_UNPRIV, 0xb8800800, 0xffe00c00, ldst_unpriv),
        MAKE_INST(INST_LDUR_LDST_UNSCALED, 0x3c400000, 0x3f600c00, ldst_unscaled),
        MAKE_INST(INST_LDUR_LDST_UNSCALED_X, 0xb8400000, 0xbfe00c00, ldst_unscaled),
        MAKE_INST(INST_LDURB_LDST_UNSCALED, 0x38400000, 0xffe00c00, ldst_unscaled),
        MAKE_INST(INST_LDURH_LDST_UNSCALED, 0x78400000, 0xffe00c00, ldst_unscaled),
        MAKE_INST(INST_LDURSB_LDST_UNSCALED, 0x38800000, 0xffa00c00, ldst_unscaled),
        MAKE_INST(INST_LDURSH_LDST_UNSCALED, 0x78800000, 0xffa00c00, ldst_unscaled),
        MAKE_INST(INST_LDURSW_LDST_UNSCALED, 0xb8800000, 0xffe00c00, ldst_unscaled),
        MAKE_INST(INST_LDXP_LDSTEXCL, 0x887f0000, 0xbfe08000, ldstexcl_op3),
        MAKE_INST(INST_LDXR_LDSTEXCL, 0x885f7c00, 0xbfe08000, ldstexcl),
        MAKE_INST(INST_LDXRB_LDSTEXCL, 0x85f7c00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_LDXRH_LDSTEXCL, 0x485f7c00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_LSL_BITFIELD, 0x53000000, 0x7f800000, bitfield),
        MAKE_INST(INST_LSL_DP_2SRC, 0x1ac02000, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_LSLV_DP_2SRC, 0x1ac02000, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_LSR_BITFIELD, 0x53000000, 0x7f800000, bitfield),
        MAKE_INST(INST_LSR_DP_2SRC, 0x1ac02400, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_LSRV_DP_2SRC, 0x1ac02400, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_MADD_DP_3SRC, 0x1b000000, 0x7fe08000, dp_3src),
        MAKE_INST(INST_MNEG_DP_3SRC, 0x1b00fc00, 0x7fe0fc00, dp_3src),
        MAKE_INST(INST_MOV_ADDSUB_IMM, 0x2a0003e0, 0x7ffffc00, addsub_imm),
        MAKE_INST(INST_MOV_LOG_IMM, 0x320003e0, 0x7f8003e0, log_imm),
        MAKE_INST(INST_MOV_LOG_SHIFT, 0x110003e0, 0x7f2003e0, log_shift),
        MAKE_INST(INST_MOV_MOVEWIDE, 0x12800000, 0x7f800000, movewide),
        MAKE_INST(INST_MOV_MOVEWIDE_X, 0x52800000, 0x7f800000, movewide),
        MAKE_INST(INST_MOVK_MOVEWIDE, 0x72800000, 0x7f800000, movewide),
        MAKE_INST(INST_MOVN_MOVEWIDE, 0x12800000, 0x7f800000, movewide),
        MAKE_INST(INST_MOVZ_MOVEWIDE, 0x52800000, 0x7f800000, movewide),
        MAKE_INST(INST_MRS_IC_SYSTEM, 0xd5300000, 0xfff00000, ic_system),
        MAKE_INST(INST_MSR_IC_SYSTEM, 0xd500401f, 0xfff8f01f, ic_system),
        MAKE_INST(INST_MSR_IC_SYSTEM_X, 0xd5100000, 0xfff00000, ic_system),
        MAKE_INST(INST_MSUB_DP_3SRC, 0x1b008000, 0x7fe08000, dp_3src),
        MAKE_INST(INST_MUL_DP_3SRC, 0x1b007c00, 0x7fe0fc00, dp_3src),
        MAKE_INST(INST_MVN_LOG_SHIFT, 0x2a2003e0, 0x7f2003e0, log_shift),
        MAKE_INST(INST_NEG_ADDSUB_SHIFT, 0x4b0003e0, 0x7f2003e0, addsub_shift),
        MAKE_INST(INST_NEGS_ADDSUB_SHIFT, 0x6b0003e0, 0x7f2003e0, addsub_shift),
        MAKE_INST(INST_NGC_ADDSUB_CARRY, 0x5a0003e0, 0x7fe0ffe0, addsub_carry),
        MAKE_INST(INST_NGCS_ADDSUB_CARRY, 0x7a0003e0, 0x7fe0ffe0, addsub_carry),
        MAKE_INST(INST_NOP_IC_SYSTEM, 0xd503201f, 0xffffffff, ic_system),
        MAKE_INST(INST_ORN_LOG_SHIFT, 0x2a200000, 0x7f200000, log_shift),
        MAKE_INST(INST_ORR_LOG_IMM, 0x32000000, 0x7f800000, log_imm),
        MAKE_INST(INST_ORR_LOG_SHIFT, 0x2a000000, 0x7f200000, log_shift),
        MAKE_INST(INST_PRFM_LDST_POS__IMMEDIATE, 0xf9800000, 0xffc00000, ldst_pos),
        MAKE_INST(INST_PRFM_LDST_REGOFF__REGISTER, 0xf8a00800, 0xffe00c00, ldst_regoff),
        MAKE_INST(INST_PRFM_LOADLIT__LITERAL, 0xd8000000, 0xff000000, loadlit),
        MAKE_INST(INST_PRFUM_LDST_UNSCALED, 0xf8800000, 0xffe00c00, ldst_unscaled),
        MAKE_INST(INST_RBIT_DP_1SRC, 0x5ac00000, 0x7ffffc00, dp_1src),
        MAKE_INST(INST_RET_BRANCH_REG, 0xd65f0000, 0xfffffc1f, branch_reg),
        MAKE_INST(INST_REV_DP_1SRC, 0x5ac00800, 0xfffffc00, dp_1src),
        MAKE_INST(INST_REV_DP_1SRC_X, 0xdac00c00, 0x7ffffc00, dp_1src),
        MAKE_INST(INST_REV16_DP_1SRC, 0x5ac00400, 0x7ffffc00, dp_1src),
        MAKE_INST(INST_REV32_DP_1SRC, 0xdac00800, 0xfffffc00, dp_1src),
        MAKE_INST(INST_ROR_DP_2SRC, 0x1ac02c00, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_ROR_EXTRACT, 0x13800000, 0x7fa00000, extract),
        MAKE_INST(INST_RORV_DP_2SRC, 0x1ac02c00, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_SBC_ADDSUB_CARRY, 0x5a000000, 0x7fe0fc00, addsub_carry),
        MAKE_INST(INST_SBCS_ADDSUB_CARRY, 0x7a000000, 0x7fe0fc00, addsub_carry),
        MAKE_INST(INST_SBFIZ_BITFIELD, 0x13000000, 0x7f800000, bitfield),
        MAKE_INST(INST_SBFM_BITFIELD, 0x13000000, 0x7f800000, bitfield),
        MAKE_INST(INST_SBFX_BITFIELD, 0x13000000, 0x7f800000, bitfield),
        MAKE_INST(INST_SDIV_DP_2SRC, 0x1ac00c00, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_SEV_IC_SYSTEM, 0xd503209f, 0xffffffff, ic_system),
        MAKE_INST(INST_SEVL_IC_SYSTEM, 0xd50320bf, 0xffffffff, ic_system),
        MAKE_INST(INST_SMADDL_DP_3SRC, 0x9b200000, 0xffe08000, dp_3src),
        MAKE_INST(INST_SMC_EXCEPTION, 0xd4000003, 0xffe0001f, exception),
        MAKE_INST(INST_SMNEGL_DP_3SRC, 0x9b20fc00, 0xffe0fc00, dp_3src),
        MAKE_INST(INST_SMSUBL_DP_3SRC, 0x9b208000, 0xffe08000, dp_3src),
        MAKE_INST(INST_SMULH_DP_3SRC, 0x9b407c00, 0xffe08000, dp_3src),
        MAKE_INST(INST_SMULL_DP_3SRC, 0x9b207c00, 0xffe0fc00, dp_3src),
        MAKE_INST(INST_STLR_LDSTEXCL, 0x889ffc00, 0xbfe08000, ldstexcl),
        MAKE_INST(INST_STLRB_LDSTEXCL, 0x89ffc00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_STLRH_LDSTEXCL, 0x489ffc00, 0xffe08000, ldstexcl),
        MAKE_INST(INST_STLXP_LDSTEXCL, 0x88208000, 0xbfe08000, ldstexcl_op4),
        MAKE_INST(INST_STLXR_LDSTEXCL, 0x8800fc00, 0xbfe08000, ldstexcl_op3),
        MAKE_INST(INST_STLXRB_LDSTEXCL, 0x800fc00, 0xffe08000, ldstexcl_op3),
        MAKE_INST(INST_STLXRH_LDSTEXCL, 0x4800fc00, 0xffe08000, ldstexcl_op3),
        MAKE_INST(INST_STNP_LDSTNAPAIR_OFFS, 0x28000000, 0x7fc00000, ldstnapair_offs),
        MAKE_INST(INST_STNP_LDSTNAPAIR_OFFS_X, 0x2c000000, 0x3fc00000, ldstnapair_offs),
        MAKE_INST(INST_STP_LDSTPAIR_INDEXED_POST, 0x28800000, 0x7fc00000, ldstpair_indexed),
        MAKE_INST(INST_STP_LDSTPAIR_INDEXED_PRE,  0x29800000, 0x7fc00000, ldstpair_indexed),
        MAKE_INST(INST_STP_LDSTPAIR_OFF, 0x29000000, 0x7fc00000, ldstpair_off),
        MAKE_INST(INST_STR_LDST_IMM9_PRE, 0xb8000c00, 0xbf600c00, ldst_imm9),
        MAKE_INST(INST_STR_LDST_IMM9_POST, 0xb8000400, 0xbfe00c00, ldst_imm9),
        MAKE_INST(INST_STR_LDST_POS, 0xb9000000, 0xbfc00000, ldst_pos),
        MAKE_INST(INST_STR_LDST_REGOFF,   0xb8200800, 0xbfe00c00, ldst_regoff),
        MAKE_INST(INST_STRB_LDST_IMM9_POST, 0x38000400, 0xffe00C00, ldst_imm9),
        MAKE_INST(INST_STRB_LDST_OFFSET,  0x39000000, 0xffc00000, ldst_pos),
        MAKE_INST(INST_STRB_LDST_REGOFF,  0x38200800, 0xffe00c00, ldst_regoff),
        MAKE_INST(INST_STRB_LDST_PRE, 0x38000c00, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_STRH_LDST_IMM_PRE, 0x78000c00, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_STRH_LDST_IMM_POST, 0x78000400, 0xffe00c00, ldst_imm9),
        MAKE_INST(INST_STRH_LDST_IMM_OFF, 0x79000000, 0xffc00000, ldst_pos),
        MAKE_INST(INST_STRH_LDST_REGOFF, 0x78200800, 0xffe00c00, ldst_regoff),
        MAKE_INST(INST_STTR_LDST_UNPRIV, 0xb8000800, 0xbfe00c00, ldst_unpriv),
        MAKE_INST(INST_STTRB_LDST_UNPRIV, 0x38000800, 0xffe00c00, ldst_unpriv),
        MAKE_INST(INST_STTRH_LDST_UNPRIV, 0x78000800, 0xffe00c00, ldst_unpriv),
        MAKE_INST(INST_STUR_LDST_UNSCALED, 0x3c000000, 0x3f600c00, ldst_unscaled),
        MAKE_INST(INST_STUR_LDST_UNSCALED_X, 0xb8000000, 0xbfe00c00, ldst_unscaled),
        MAKE_INST(INST_STURB_LDST_UNSCALED, 0x38000000, 0xffe00c00, ldst_unscaled),
        MAKE_INST(INST_STURH_LDST_UNSCALED, 0x78000000, 0xffe00c00, ldst_unscaled),
        MAKE_INST(INST_STXP_LDSTEXCL, 0x88200000, 0xbfe08000, ldstexcl_op4),
        MAKE_INST(INST_STXR_LDSTEXCL, 0x88007c00, 0xbfe08000, ldstexcl_op3),
        MAKE_INST(INST_STXRB_LDSTEXCL, 0x8007c00, 0xffe08000, ldstexcl_op3),
        MAKE_INST(INST_STXRH_LDSTEXCL, 0x48007c00, 0xffe08000, ldstexcl_op3),
        MAKE_INST(INST_SUB_ADDSUB_EXT, 0x4b200000, 0x7fe00000, addsub_ext),
        MAKE_INST(INST_SUB_ADDSUB_IMM, 0x51000000, 0x7f000000, addsub_imm),
        MAKE_INST(INST_SUB_ADDSUB_SHIFT, 0x4b000000, 0x7f200000, addsub_shift),
        MAKE_INST(INST_SUBS_ADDSUB_EXT, 0x6b200000, 0x7fe00000, addsub_ext),
        MAKE_INST(INST_SUBS_ADDSUB_IMM, 0x71000000, 0x7f000000, addsub_imm),
        MAKE_INST(INST_SUBS_ADDSUB_SHIFT, 0x6b000000, 0x7f200000, addsub_shift),
        MAKE_INST(INST_SVC_EXCEPTION, 0xd4000001, 0xffe0001f, exception),
        MAKE_INST(INST_SXTB_BITFIELD, 0x13001c00, 0x7fbffc00, bitfield),
        MAKE_INST(INST_SXTH_BITFIELD, 0x13003c00, 0x7fbffc00, bitfield),
        MAKE_INST(INST_SXTW_BITFIELD, 0x93407c00, 0xfffffc00, bitfield),
        MAKE_INST(INST_SYS_IC_SYSTEM, 0xd5080000, 0xfff80000, ic_system),
        MAKE_INST(INST_SYSL_IC_SYSTEM, 0xd5280000, 0xfff80000, ic_system),
        MAKE_INST(INST_TBNZ_TESTBRANCH, 0x37000000, 0x7f000000, testbranch),
        MAKE_INST(INST_TBZ_TESTBRANCH, 0x36000000, 0x7f000000, testbranch),
        MAKE_INST(INST_TLBI_IC_SYSTEM, 0xd5080000, 0xfff80000, ic_system),
        MAKE_INST(INST_TST_LOG_IMM, 0x7200001f, 0x7f80001f, log_imm),
        MAKE_INST(INST_TST_LOG_SHIFT, 0x6a00001f, 0x7f20001f, log_shift),
        MAKE_INST(INST_UBFIZ_BITFIELD, 0x53000000, 0x7f800000, bitfield),
        MAKE_INST(INST_UBFM_BITFIELD, 0x53000000, 0x7f800000, bitfield),
        MAKE_INST(INST_UBFX_BITFIELD, 0x53000000, 0x7f800000, bitfield),
        MAKE_INST(INST_UDIV_DP_2SRC, 0x1ac00800, 0x7fe0fc00, dp_2src),
        MAKE_INST(INST_UMADDL_DP_3SRC, 0x9ba00000, 0xffe08000, dp_3src),
        MAKE_INST(INST_UMNEGL_DP_3SRC, 0x9ba0fc00, 0xffe0fc00, dp_3src),
        MAKE_INST(INST_UMSUBL_DP_3SRC, 0x9ba08000, 0xffe08000, dp_3src),
        MAKE_INST(INST_UMULH_DP_3SRC, 0x9bc07c00, 0xffe08000, dp_3src),
        MAKE_INST(INST_UMULL_DP_3SRC, 0x9ba07c00, 0xffe0fc00, dp_3src),
        MAKE_INST(INST_UXTB_BITFIELD, 0x53001c00, 0xfffffc00, bitfield),
        MAKE_INST(INST_UXTH_BITFIELD, 0x53003c00, 0xfffffc00, bitfield),
        MAKE_INST(INST_UXTW_LOG_SHIFT, 0xD3407C00, 0x7f2003e0, bitfield),
        MAKE_INST(INST_WFE_IC_SYSTEM, 0xd503205f, 0xffffffff, ic_system),
        MAKE_INST(INST_WFI_IC_SYSTEM, 0xd503207f, 0xffffffff, ic_system),
        MAKE_INST(INST_YIELD_IC_SYSTEM, 0xd503203f, 0xffffffff, ic_system)
    };
}
