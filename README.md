**Blog**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[![](https://img.shields.io/badge/Read%20My-Blog-ff69b4?logo=Hugo&style=for-the-badge)](https://blog.roycedavis.net/)

**Twitter:**&nbsp;&nbsp;[![](https://img.shields.io/twitter/follow/r3dy__?logo=Twitter&style=for-the-badge)](https://twitter.com/@r3dy__)

**Discord:**  [![](https://dcbadge.vercel.app/api/server/D5QTtWEwxZ)](https://discord.gg/D5QTtWEwxZ) 

**Book:**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[![](https://img.shields.io/badge/Art%20of%20Network-Penetration%20Testing-orange?style=for-the-badge&logo=Amazon)](https://amzn.to/43wQOYk)


# 1. Capsulecorp Pentest

The Capsulecorp Pentest is a small virtual network managed by Vagrant and Ansible. It contains five virtual machines, including one Linux attacking system running Xubuntu and 4 Windows 2019 servers configured with various vulnerable services. This project can be used to learn network penetration testing as a stand-alone environment but is ultimatly designed to complement my book [The Art of Network Penetration Testing](https://bit.ly/38N9S9e).

![](https://raw.githubusercontent.com/R3dy/capsulecorp-pentest/assets/windows%20environment.png)

## Why is this cool?
Setting up a virtual network to learn penetration testing can be tedious as well as time/resource consuming. Everything in the capsulecorp environment is pretty much done for you already. Once you get Vagrant, Ansible and VirtualBox installed on your machine you only need to run a couple of `vagrant` commands to have a fully functioning Active Directory domain that you can use for hacking/learning/pentesting etc.

## 1.1. Requirements
In order to use the Capsulecorp Pentest network you must have the following:

* VirtualBox
  * [https://www.virtualbox.org/wiki/Downloads](https://www.virtualbox.org/wiki/Downloads)
* Vagrant
  * [https://www.vagrantup.com/downloads.html](https://www.vagrantup.com/downloads.html)
* Ansible
  * [https://docs.ansible.com/ansible/latest/installation_guide/index.html](https://docs.ansible.com/ansible/latest/installation_guide/index.html)

* Quad-core CPU with 16GB RAM is recommended.  
  * With 8GB or less you could still use the project but ***only run 2 or 3 VMs at a time***.  
  * For All VMs running at once 16GB is required.

## 1.2. Current Functionality
* Active directory domain with one DC and 3 server members. All windows server have evaluation licenses, which are activated on installation (for 180 days)
  * Domain Controler: `goku.capsulecorp.local`
  * Server 01: `vegeta.capsulecorp.local`
  * Server 02: `gohan.capsulecorp.local`
  * Server 03: `trunks.capsulecorp.local`
  * Wrkstn 01: `tien.capsulecorp.local`
* Vulnerable Jenkins server on `vegeta`
* Vulnerable Apache Tomcat server on `trunks`
* Vulnerable MSSQL server on `gohan`
* Vulnerable MS17-010 on `tien`
* Xubuntu pentest system running XRDP.
  * Metasploit
  * CrackMapExec
  * Nmap
  * Remmina RDP client
  * RVM
  * Python/Pip/Pipenv
  * Impacket

## 1.3. OSX Configuration
In order to manage Windows hosts you'll have to install `pywinrm` with pip inside the ansible virtual environment

```bash
source ~/ansible/bin/activate
pip install pywinrm
deactivate
```

# 2. Installation
**YouTube Setup Tutorial**

[![](https://img.shields.io/youtube/channel/views/UCi0_EWBSYdNomzAtV0nYOPA?logo=Youtube&style=for-the-badge)](https://m.youtube.com/watch?v=An4QvztvDaE&t=0s)

For a detailed installation walkthrough check out

* [MacOS Setup Guide](https://github.com/R3dy/capsulecorp-pentest/wiki/MacOS-Setup-Guide)

* [Windows Setup Guide](https://github.com/R3dy/capsulecorp-pentest/wiki/Windows-Setup-Guide)

## 2.1. Configure the windows hosts
The first thing you should do is bring up and provision Goku the domain controller. This system will likely take the longest to bring up because the dcpromo stuff just takes a while.

***Note***: if you are running vagrant with sudo. use ```sudo -E vagrant``` option to run vagrant

Bring up the VM

	vagrant up goku

Provision the VM

	vagrant provision goku

Repeat the above two commands for gohan, vegeta and trunks.

***...WARNING...***

This section of the provision is expected to take a while because after a dcpromo it takes a long time for the system to reboot.

```bash
TASK [promotedc : Set a static address to 172.28.128.100] **********************
changed: [goku]

TASK [promotedc : Change hostname to goku] *************************************
ok: [goku]

TASK [promotedc : Install Active Directory Services] ***************************
ok: [goku]

TASK [promotedc : Promote goku to domain controller] ***************************
changed: [goku]

TASK [promotedc : Reboot after promotion] **************************************
```

## 2.2. Configure your pentest platform

Bring up the virtual machines using Vagrant. First cd into the project directory, for example: `cd ~/capsulecorp-pentest`.  Take note of the RDP port that gets forwarded to your localhost.

    vagrant up pentest

Provision the pentest machine.

    vagrant provision pentest

You can access your pentest machine either using your preferred RDP client to connect to the xrdp listener or via SSH with.

	vagrant ssh pentest
