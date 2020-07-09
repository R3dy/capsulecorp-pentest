# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  
  winsrv = "royce/capsulecorp-winsrv"
  winwks = "royce/capsulecorp-win7"
  dcmem = "0.0.7-alpha-dc-member"
  vms = [{name: "goku", ip: "172.28.128.100", box: winsrv, version: "0.0.5-alpha"},
          {name: "gohan", ip: "172.28.128.101", box: winsrv, version: dcmem},
          {name: "vegeta", ip: "172.28.128.102", box: winsrv, version: dcmem},
          {name: "trunks", ip: "172.28.128.103", box: winsrv, version: dcmem},
          {name: "tien", ip: "172.28.128.104", box: winwks, version: "0.0.2-alpha"},
          {name: "raditz", ip: "172.28.128.105", box: winsrv, version: dcmem}]

  vms.each do |vm|
    config.vm.define vm[:name] do |system|
      system.vm.network "private_network", ip: vm[:ip]
      system.vm.box = vm[:box]
      system.vm.box_version = vm[:version]
      system.vm.guest = :windows
      system.vm.communicator = "winrm"
      system.vm.network :forwarded_port, guest: 3389, host: 3389, auto_correct: true
      system.vm.provision "ansible" do |ansible|
        ansible.playbook = "#{vm[:name]}.yml"
      end 
    end
  end

  config.vm.define "pentest" do |box|
    box.vm.box = "royce/capsulecorp-pentest"
    box.vm.box_version ="0.0.4-alpha"
    box.vm.network "private_network", ip: "172.28.128.200"
    box.vm.network :forwarded_port, guest: 3389, host: 3389, auto_correct: true
    box.ssh.username = "pentest"
    box.ssh.private_key_path = "ssh/capsulecorp_id_rsa"
    box.vm.provision "ansible" do |ansible|
      ansible.playbook = "pentest.yml"
    end
  end

end
