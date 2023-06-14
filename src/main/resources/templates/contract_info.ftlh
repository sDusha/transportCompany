<#import "blocks/template.ftlh" as t>
<@t.template user>
<!-- неплохо бы еще фото -->
<h4>Информация о заказе:</h4>
<div><b>Цена:</b> ${contract.price}<br></div>
<div><b>Откуда:</b> ${contract.fromAddress}<br></div>
<div><b>Владелец заказа:</b><a href="/user/${contract.user.id}">${contract.user.name}</a> <br></div>
<div><b>Куда:</b> ${contract.toAddress}<br></div>
<div><b>Статус:</b> ${contract.status}<br></div>

    <#if user.isAdmin()>
        <#list statuses as status>
            <div>
                <div class="form-check">
                    <input id="${status}" name="status" type="radio" class="form-check-input" checked="" required="">
                    <label class="form-check-label" for="${status}">${status}</label>
                </div>
            </div>
        </#list>
    </#if>
        <#if user.isOwner()>
        <#list statuses as status>
            <div>
                <div class="form-check">
                    <input id="${status}" name="status" type="radio" class="form-check-input" checked="" required="">
                    <label class="form-check-label" for="${status}">${status}</label>
                </div>
            </div>
        </#list>
    </#if>

</@t.template>