# Copyright (C) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit local-git

SRCREV = "604cec4004abe5a96c734f2fab7b74809d2d742f"
BRANCH = "picocom/rk3399-master"
SRC_URI = " \
	git://git@github.com/picocom-chips/linux.git;protocol=ssh;branch=${BRANCH}; \
"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
