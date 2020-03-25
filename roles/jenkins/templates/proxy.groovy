mport jenkins.model.*
import hudson.*

def instance = Jenkins.getInstance()
// Use Corporate HPE proxy link.
def proxy_name = "{{ jenkins_web_proxy }}"
def proxy_port = {{ jenkins_web_proxy_port }}

def proxy  = instance.proxy

if ( proxy == null )
  {
   proxy = new ProxyConfiguration(proxy_name, proxy_port)
   instance.proxy = proxy
  }
else
  {
   proxy.name = proxy_name
   proxy.port = proxy_port
  }

println('proxy.groovy -- Proxy set to ' + proxy_name + ':' + proxy_port)
instance.save()
