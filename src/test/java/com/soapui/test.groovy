#Global Prop 

<web:sCountryISOCode>${#Global#global_code}</web:sCountryISOCode>

<web:sCountryISOCode>${#Project#project_code}</web:sCountryISOCode>


<web:sCountryISOCode>${#TestSuite#suite_code}</web:sCountryISOCode>

<web:sCountryISOCode>${#TestCase#suite_code}</web:sCountryISOCode>

<web:sCountryISOCode>${Proper#suite_code}</web:sCountryISOCode>

def projectpath=testRunner.testCase.testSuite.project

log.info testRunner.testCase.testSuite.project.testSuites["Suite 1"].getPropertyValue("code")

log.info testRunner.testCase.testSuite.project.testSuites["Suite 1"].testCases["Cases 1"].testSteps["Step 1"].getPropertyValue("Request")

log.info testRunner.testCase.testSuite.project.getPropertyValue("code")

log.info testRunner.testCase.testSuite.getPropertyValue("code")

log.info testRunner.testCase.getPropertyValue("code")

context.expand('${Properties#country}')

log.info testRunner.testCase.testSuite.project.name

context.getCurrentStep().getLabel()


com.eviware.soapui.SoapUI.globalProperties.setPropertyValue('a','test')

testRunner.testCase.testSteps['Add'].setProperty('username','1');


//request 


//response 
Fri Nov 05 18:42:27 IST 2021:INFO:<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <m:CountryISOCodeResponse xmlns:m="http://www.oorsprong.org/websamples.countryinfo">
         <m:CountryISOCodeResult>IN</m:CountryISOCodeResult>
      </m:CountryISOCodeResponse>
   </soap:Body>
</soap:Envelope>



import com.eviware.soapui.support.XmlHolder
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext

def request= testRunner.testCase.testSteps["CountryISOCode"].getPropertyValue('Request')
def country=context.expand('${Properties#country}')

def requestxml=new XmlHolder(request)
requestxml.setNodeValue("//web:CountryISOCode//web:sCountryName",country)
def finalxml=requestxml.getXml()


testRunner.testCase.testSteps['CountryISOCode'].setPropertyValue('Request', finalxml)
def finalreq=testRunner.testCase.testSteps['CountryISOCode']
def context= new WsdlTestRunContext(finalreq)
finalreq.run(testRunner,context)


def response= testRunner.testCase.testSteps["CountryISOCode"].getPropertyValue('Response')
def responsexml=new XmlHolder(response)
def responsevalue=responsexml.getNodeValue("//*:CountryISOCodeResult")

log.info responsevalue

def secondrequest= testRunner.testCase.testSuite.testCases["CountryName TestCase"].testSteps["CountryName"].getPropertyValue('Request')
def secondrequestxml=new XmlHolder(secondrequest)
secondrequestxml.setNodeValue("//web:CountryName//web:sCountryISOCode",responsevalue)
def finalxml2=secondrequestxml.getXml()

def finalreq2=testRunner.testCase.testSuite.testCases['CountryName TestCase'].testSteps['CountryName']
def context2= new WsdlTestRunContext(finalreq2)
finalreq2.run(testRunner,context2)

def response2= testRunner.testCase.testSuite.testCases['CountryName TestCase'].testSteps['CountryName'].getPropertyValue('Response')
def responsexml2=new XmlHolder(response2)
def responsevalue2=responsexml2.getNodeValue("//m:CountryNameResponse//m:CountryNameResult")
log.info "final"+responsevalue2


