# Mediatek MT7623 u-boot

include u-boot-mtk.inc

PROVIDES += " virtual/bootloader u-boot"

# Some MT7623 preloader seem to require u-boot to have a custom 512 byte
# header created by MTK's 'mkimage' app:
do_compile_append_oxygen-ap () {
    ${S}/mkimage u-boot.bin UBOOT > u-boot-mtk.bin
    mv u-boot-mtk.bin u-boot.bin
}
do_compile_append_mt7623-evb () {
    ${S}/mkimage u-boot.bin UBOOT > u-boot-mtk.bin
    mv u-boot-mtk.bin u-boot.bin
}


do_compile_append () {
    oe_runmake env
    tools/mkenvimage -s 16384 -o u-boot-env-${MACHINE}.bin ${S}/${MACHINE}-env.txt
}

do_deploy_append () {
    install ${B}/u-boot-env-${MACHINE}.bin ${DEPLOYDIR}
}
