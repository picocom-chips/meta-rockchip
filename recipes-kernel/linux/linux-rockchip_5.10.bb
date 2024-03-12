# Copyright (C) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit local-git

SRCREV_NON_RT = "9c81604c6136f2443665514de7e2d2e90bf331cc"
BRANCH_NON_RT = "picocom/rk3399-master"

SRCREV_RT = "f8a52a753aecdb9c4465f4fb69c1a9df33f5ecd6"
BRANCH_RT = "picocom/rk3399-rt-master"

SRCREV = "${@d.getVar('SRCREV_RT') if d.getVar('ENABLE_PREEMPT_RT') == 'true' else d.getVar('SRCREV_NON_RT')}"
BRANCH = "${@d.getVar('BRANCH_RT') if d.getVar('ENABLE_PREEMPT_RT') == 'true' else d.getVar('BRANCH_NON_RT')}"
SRC_URI = " \
	git://git@github.com/picocom-chips/linux.git;protocol=ssh;branch=${BRANCH}; \
	file://${THISDIR}/files/cgroups.cfg \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.10"

SRC_URI:append = " ${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', \
		   'file://${THISDIR}/files/ext4.cfg', \
		   '', \
		   d)}"

do_patch:append() {
	sed -i 's/-I\($(BCMDHD_ROOT)\)/-I$(srctree)\/\1/g' \
		${S}/drivers/net/wireless/rockchip_wlan/rkwifi/bcmdhd/Makefile
}
