:: #=========================================================================#
::  #               This file is part of Adult Content Filter               #
::  #        https://github.com/federicolencina/adult-content-filter        #
::  #                                                                       #
::  #                      GNU General Public License                       #
::  #                                                                       #
::  #     This program is free software; you can redistribute it and/or     #
::  #    modify it under the terms of the GNU General Public License as     #
::  #    published by the Free Software Foundation; either version 3 of     #
::  #          the License, or at your option any later version.            #
::  #                                                                       #
::  #  This program is distributed in the hope that it will be useful, but  #
::  #       WITHOUT ANY WARRANTY; without even the implied warranty of      #
::  #          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.         #
::  #          See the GNU General Public License for more details.         #
::  #                                                                       #
::  #        You should have received a copy of the GNU General Public      #
::  #              License along with this program. If not, see             #
::  #                    <https://www.gnu.org/licenses/>.                   #
::  #                                                                       #
::  #                    Copyright 2024 Federico Lencina                    #
:: #=========================================================================#

@ECHO OFF
:: If the script is not running with elevated permissions, auto restart and request for elevated permissions.
net session >nul 2>&1
if %errorLevel% neq 0 (
    powershell -Command "Start-Process '%0' -Verb RunAs"
    exit /B
)
:: Set IPv4 & IPv6 DNS Server to DHCP (Default) in Ethernet interface. These processes are not verbose = nul 2>&1.
netsh interface ipv4 set dnsservers name="Ethernet" source=DHCP > nul 2>&1
netsh interface ipv6 set dnsservers name="Ethernet" source=DHCP > nul 2>&1
:: Set additional IPv4 & IPv6 DNS Server DHCP (Default) in Ethernet interface. These processes are not verbose = nul 2>&1.
netsh interface ipv4 add dnsservers name="Ethernet" address=DHCP > nul 2>&1
netsh interface ipv6 add dnsservers name="Ethernet" address=DHCP > nul 2>&1

:: Set additional IPv4 & IPv6 DNS Server to DHCP (Default) in WiFi interface. These processes are not verbose = nul 2>&1.
netsh interface ipv4 set dnsservers name="WiFi" source=DHCP > nul 2>&1
netsh interface ipv6 set dnsservers name="WiFi" source=DHCP > nul 2>&1
:: Set additional IPv4 & IPv6 DNS Server to DHCP (Default) in WiFi interface. These processes are not verbose = nul 2>&1.
netsh interface ipv4 add dnsservers name="WiFi" address=DHCP > nul 2>&1
netsh interface ipv6 add dnsservers name="WiFi" address=DHCP > nul 2>&1

:: "Flush" DNS Cache to apply changes. This process is not verbose = nul.
ipconfig /flushdns > nul