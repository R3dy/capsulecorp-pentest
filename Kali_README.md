## 1. VirtualBox
For now VirtualBox is the supported vagrant provider. If you absolutely have to use something else, you'll simply need to go through the process of converting the baseboxes found here [https://app.vagrantup.com/royce](https://app.vagrantup.com/royce ) and export/import them into VMware. Then follow Vagrant's documentation for packaging VMware base boxes.

### 1.1 Install the latest VirtualBox
  1. Open up kali terminal and type `cd ~`
  2. Run the command `sudo apt update -y`
  3. Run the command `sudo apt dist-upgrade -y`
  4. Run the command `sudo apt install virtualbox -y`
  5. Run the command `sudo reboot`

## 2. Ansible
### 2.1 Install pip 
  1. Open up kali terminal and type `cd ~`
  2. Run the command `sudo apt update`
  3. Run the command `sudo apt dist-upgrade -y`
  4. Create a directory for the pip installer `mkdir pipinstaller`
  5. download the pip installer `curl https://bootstrap.pypa.io/get-pip.py -o pipinstaller/get-pip.py`
  6. install pip `python pipinstaller/get-pip.py --user`
  7. Make pip executable from your path `echo 'export PATH="${PATH}:/home/pentest/.local/bin"' >> ~/.bashrc`

### 2.2 Install Ansible
  1. Run the command `~/.bashrc`
  2. Install ansible using pip3 `pip3 install ansible --user`

## 3. Vagrant
### 3.1 Install Vagrant
  1. Run the command `sudo apt install vagrant -y`
  2. Run the command `vagrant -v`. Should see similar to the next: 
```zsh
Vagrant 2.3.4
```

### 3.2 Vagrant plugins
These may or may not be required for you but I ran into some errors and while troubleshooting I found various error messages regarding these plugins and installing them removed the error messages.
  1. Run the command `vagrant plugin install winrm`
  2. Run the command `vagrant plugin install winrm-fs`
  3. Run the command `vagrant plugin install winrm-elevated`

## 4. CapsuleCorp
### 4.1 Edit Vagrantfile  
  1. Download the repository `git clone https://github.com/R3dy/capsulecorp-pentest.git`
  2. Run the command `nano ./capsulecorp-pentest/Vagrantfile`
  3. Edit line 11, to remove the comment simbol. It should look like the following: `winsrv = "StefanScherer/windows_2019"`
  4. Comment line 12, it should look like the following: `#winsrv = "gusztavvargadr/windows-server"`
  5. If your hardware has a total amount of 8GB Ram, you can also edit line 63, setting `vb.memory = "2048"` to "1024".
  6. Finally, press `ctrl+x`, type "y" and confirm.
### 4.2 Provisioning VM's
  1. For provisioning the VM's on the correct order, follow the VM documentation.
  2. Make sure to set the correct provider, such as: `sudo -E vagrant <box.name> --provider=virtualbox`
  3. For acessing your VM's on VirtualBox, run the command `sudo virtualbox`
  4. Also, if your host system is Kali, there is no need for the Pentest VM. Whatsoever you will need to configure your host system to be able to access the domain.
