#=========================================================================#
 #               This file is part of Adult Content Filter               #
 #        https://github.com/federicolencina/adult-content-filter        #
 #                                                                       #
 #                      GNU General Public License                       #
 #                                                                       #
 #     This program is free software; you can redistribute it and/or     #
 #    modify it under the terms of the GNU General Public License as     #
 #    published by the Free Software Foundation; either version 3 of     #
 #          the License, or at your option any later version.            #
 #                                                                       #
 #  This program is distributed in the hope that it will be useful, but  #
 #       WITHOUT ANY WARRANTY; without even the implied warranty of      #
 #          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.         #
 #          See the GNU General Public License for more details.         #
 #                                                                       #
 #        You should have received a copy of the GNU General Public      #
 #              License along with this program. If not, see             #
 #                    <https://www.gnu.org/licenses/>.                   #
 #                                                                       #
 #                    Copyright 2024 Federico Lencina                    #
#=========================================================================#

# Set IPv4 DNS Server to Cloudflare 1.1.1.3 in "Wired Connection 1" Profile.
nmcli connection modify "Wired connection 1" ipv4.dns "1.1.1.3 1.0.0.3"
# Set IPv6 DNS Server to Cloudflare 1.1.1.3 in "Wired Connection 1" Profile.
nmcli connection modify "Wired connection 1" ipv6.dns "2606:4700:4700::1113 2606:4700:4700::1003"
# Restart the connection to apply changes.
nmcli connection down "Wired connection 1"
nmcli connection up "Wired connection 1"