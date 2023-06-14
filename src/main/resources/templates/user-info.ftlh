<#import "blocks/template.ftlh" as t>
<@t.template user>
<h3>Подробная информация о пользователе ${user.name}</h3>
<b>Номер телефона: </b> ${user.getPhoneNumber()}<br>
<b>Email: </b> ${user.getEmail()}<br>
<#if user.getInformation()??>
    <b>Прочая информация: </b> ${user.getInformation()}<br>
</#if>

<hr>
<h4>Контракты пользователя ${user.name}</h4>
<#list contracts as contract>
    <div>
        <p>Из ${contract.fromAddress} в ${contract.toAddress}</p>
        <p>Статус: ${contract.status}</p>
        <a href="/contract/${contract.getId()}">подробнее</a>
    </div>
    <#else>
    <h3>Нет контрактов</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage} </h2>
</#if>
</@t.template>