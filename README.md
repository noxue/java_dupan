## 获取到二维码图片地址

* 请求

`https://passport.baidu.com/v2/api/getqrcode?lp=pc&qrloginfrom=pc&gid=A08127A-D125-4F42-B3E8-492A8238F808&apiver=v3`


* 返回：

`{"imgurl":"passport.baidu.com\/v2\/api\/qrcode?sign=0b68935c5cec774508f36b50970ec8e4&lp=pc&qrloginfrom=pc","errno":0,"sign":"0b68935c5cec774508f36b50970ec8e4","prompt":"\u767b\u5f55\u540e\u5a01\u9a6c\u5c06\u83b7\u5f97\u767e\u5ea6\u5e10\u53f7\u7684\u516c\u5f00\u4fe1\u606f\uff08\u7528\u6237\u540d\u3001\u5934\u50cf\uff09"}`



## 判断是否扫码登陆成功

* 循环请求

`https://passport.baidu.com/channel/unicast?channel_id=883b690a169d0e3657cfed65f578381c&tpl=netdisk&gid=A08127A-D125-4F42-B3E8-492A8238F808&callback=&apiver=v3&tt=1595247463569&_=1595247463569`


* 返回

({"errno":1})



## 扫码后访问这个地址获取cookie

* 请求

`https://passport.baidu.com/v3/login/main/qrbdusslogin?v=1595252895074&bduss=66c4676a0ba5f0c75df173c7140a956a&u=https%253A%252F%252Fpan.baidu.com%252Fdisk%252Fhome&loginVersion=v4&qrcode=1&tpl=netdisk&apiver=v3&tt=1595252895074&traceid=&time=1595252895&alg=v3&sig=YU04WUl3TG9HeVBpUmZkeTBTV0g4MExrU2hKLzRkOHAxMjRFMzk2MmFoM2ZlVGJJRkVXelVIYnFwYm96ejc0eg%3D%3D&elapsed=37&shaOne=0031d322a5953f842f748493f90a9a1172b55d7f&callback=bd__cbs__3h4j2j`

* 返回

`{"errInfo":{"no":"0","msg":""},"code":"110000","message":"",'data':{"u":"https:\/\/pan.baidu.com\/disk\/home","userName":"","hao123Param":"bDRWV0V6Tkc5RlYxRi1WRzVzYlRadFlURkdTM0pyYWxwLWN6ZFBha1ZJWVdGT1Z6RjFVMWxrYzJ0UFJERm1TVkZCUVVGQkpDUUFBQUFBQUFBQUFBRUFBQUFISkozcHNydlJwODM0emZqRnpERUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFDU3JGVjhrcXhWZlpq","bdusssign":"","authtoken":"","session":{"version":"v3","actionType":"","canshare":"1","authsid":"3YyEZXqujf5EK5kppDc4YH0Im4DU8LdK","needvcode":"0","bduss":"l4VWEzNG9FV1F-VG5sbTZtYTFGS3Jralp-czdPakVIYWFOVzF1U1lkc2tPRDFmSVFBQUFBJCQAAAAAAAAAAAEAAAAHJJ3psrvRp834zfjFzDEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSrFV8kqxVfZj","ptoken":"faad843f6062f4914f209cddc163f4c5","stoken":"7fedb26d9c3380f561066c17a642f1ef5f82cc2b0f9077501f014c35b7f08a84","ubi":"fi_PncwhpxZ~TaJc1pC-xcrO5JQCVzeTCEp","stokenList":"[&quot;tb#50197dd6dd8b1452a13392a3558dea105f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;pp#7fedb26d9c3380f561066c17a642f1ef5f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;bp#63ad742b8b7c1485ef477c486e02a5895f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;netdisk#07ae49941cdc87098edf568333a2868dcad180568e0bbb8f6f139b573e184355&quot;,&quot;cloudforbusiness#9843240c22d8d00d4d6f6603cd8046e8c2f033a39cc99624fa024860b5734163&quot;,&quot;bdwm#23ec16db4c8310a97acc58394f1ae4ad5f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;waimai#8a5791c0b88ac6064c7946549920d6e40464cbb0213d04a5fc51edc61d19f2df&quot;,&quot;bdwalletsdk#cdaa779219b924dd6f1c09272f06a33b6a10b84c63e3e15445aea5ca456f26bd&quot;,&quot;baiduwalletapp#a26b830368a0b7bb79a11ef9762c46451099ef8bd6352df57902d9bb688c667a&quot;,&quot;baidugushitong#a26b830368a0b7bb79a11ef9762c4645159970c60a6102a4edf0508d4c87f1a2&quot;,&quot;bdbus#b2f32d9f424eaffa57e098a482abae59cdada7d521cdb18e7a92bd33be04b545&quot;,&quot;fund#07ade67448e7c16c1c7b25bead3a4fbf5f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;nuomi#94ec0e55c0aa2c5a46ee97311203443704edf91b4bcc661c0e5d896403ec5029&quot;,&quot;licai#c4ee7a9a1547defd4c7fb7c797957f7d04edf91b4bcc661c0e5d896403ec5029&quot;,&quot;asset#bdb3b29d47be7c72d856b2bc92f4e779641a3f5a1bba04da4b2cf9f9237ccc82&quot;,&quot;hao123#b6f76c7ba560d3a273919d5d134068f536e6eb160d15b22f44435e1194e2be5e&quot;,&quot;pcs#bd07098ca05e7a4cf7e5414df607d53b5f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;dev#16b125a27e5895209e43b3027c7c85e95f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;fbuym#e0bcb23ea11f2ab68214b83482c3d4f16be6529c7670401c8f78c685a11c8883&quot;,&quot;licaiapp#c4ee7a9a1547defd4c7fb7c797957f7de18130a368704b758bb2ad5b494d8268&quot;,&quot;mybox#3667d63c2e66c767749e35d42f48db03f242709b694942820434e3ff5e46085f&quot;,&quot;iitnightingale#308e1230cc538a137441186ee590186844374c87700a7550d34fec9b8afe7264&quot;,&quot;dianying#40ea196e891e94b4ea0049c78bed6c057bf79a2217c7f7d4b367031dc75c062b&quot;,&quot;mall#d050016c36381356a4b4a1018d6826a55f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;lv#f6c5a59dd0cfe80a66af793ea5b7b1995f82cc2b0f9077501f014c35b7f08a84&quot;,&quot;cmovie#bd4bc72eef18c1f6258ba7701f98e7b7cbdfaa89634540a74c17cb17d87bf771&quot;,&quot;mapsafe#c78ee9ce93825f44a14213cc5aa81e7c42af472207d2bc6b730f88d37a75e1a6&quot;,&quot;im_hi#66a43f127bfccc86736fd2040d33f91a04edf91b4bcc661c0e5d896403ec5029&quot;,&quot;ppapp#bdb440f914a723283e12834965926cfdb23d9ff0ea7f204a8cfb8432c990868c&quot;,&quot;licaient#c4ee7a9a1547defd4c7fb7c797957f7d2984177d3ea0fdce78a5ec2547039458&quot;,&quot;album#1c63ae1ca78c74cf0b202a5f2cd0d1bc6be6529c7670401c8f78c685a11c8883&quot;,&quot;aduqa#2179d0b2f658a0adadbfbbb2c732a899aca33c74d12d229a9fa526c7404a5681&quot;]"},"user":{"username":"不学网网盘1","weakpass":"","userId":"3919389703","livinguname":"","portraitUrl":"https:\/\/himg.bdimg.com\/sys\/portrait\/item\/pp.1.5b01342d.zfMYmwlLQPSLDBMQI5H3iw.jpg","portraitSign":"pp.1.5b01342d.zfMYmwlLQPSLDBMQI5H3iw","displayName":"不学网网盘1"}},"traceid":""}`



## 获取用户信息

* 请求

`https://pan.baidu.com/disk/home`


* 从返回的内容中提取如下内容

`{"loginstate":1,"username":"\u4e0d\u5b66\u7f51\u7f51\u76d81","third":0,"flag":1,"file_list":null,"uk":619530562,"task_key":"43635e4da9c96a37bb3c25f81809b1f89d264663","task_time":1595255589,"sampling":false,"bdstoken":"04a0f66e4baf2ab09e76c8d887585bfa","is_vip":0,"bt_paths":null,"applystatus":1,"sign1":"a67f175f7d0c9fc63a8519e8d414e8253d8f1843","sign2":"function s(j,r){var a=[];var p=[];var o=\"\";var v=j.length;for(var q=0;q<256;q++){a[q]=j.substr((q%v),1).charCodeAt(0);p[q]=q}for(var u=q=0;q<256;q++){u=(u+p[q]+a[q])%256;var t=p[q];p[q]=p[u];p[u]=t}for(var i=u=q=0;q<r.length;q++){i=(i+1)%256;u=(u+p[i])%256;var t=p[i];p[i]=p[u];p[u]=t;k=p[((p[i]+p[u])%256)];o+=String.fromCharCode(r.charCodeAt(q)^k)}return o};","sign3":"d76e889b6aafd3087ac3bd56f4d4053a","timestamp":1595255589,"timeline_status":1,"face_status":0,"srv_ts":1595255589,"need_tips":null,"is_year_vip":0,"show_vip_ad":0,"vip_end_time":null,"is_evip":0,"is_svip":0,"is_auto_svip":0,"activity_status":0,"photo":"https:\/\/dss0.bdstatic.com\/7Ls0a8Sm1A5BphGlnYG\/sys\/portrait\/item\/netdisk.1.e983bbbf.Tw4Cpz2QxLsFrbx95-Rc4w.jpg","curr_activity_code":0,"activity_end_time":0,"token":"b455TOnl4xWx+nqMMmwB\/0Rv\/Vc0FXJVQa3MjnEUpHHSsZjXrektqq2hFOIUvnj\/frNIzIf+G2gNR8qfvo+yIsGlKXX7emCTI4N02rtVYtSOm5I95zR8pb9aTGva0amXU+cGu+Ldj0d\/bmuSOatcMK6EjDm38yRXrzf5527B1c8fJPFHhBDA1\/lGQ7JmmtZ6+UftBV4F2BTKosg1k3EpFfZ7Mqj\/4SWZy6FZzxrUDyH2aBp9Ob3iWvjcP3JA0eZY0SiyIqqCeHRN7i2o2skf4fCF\/Up528fZaDw","sharedir":0,"pansuk":"N_oBWSCb1kQdLB9lXGOKGQ","vol_autoup":"0","wpsauth":null,"wpssamping":true,"hit_ogc":false,"skinName":"skycloud","urlparam":[],"XDUSS":"pansec_DCb740ccc5511e5e8fedcff06b081203-LWcYnDGhpxnilm8tvRj8KK2SzZ78cCNmTHyJAfjmHectkRGKEMkvxGKHZkrKR9HqBKtUkClxLAXm%2BhHPsoAn8r1xssHSejgkaC%2FqFg5Bps2MzQM0v50mAjHxesxUreEe67Sqe577oKMSO%2FRZypy9Nm8qjFSW8im%2B4t7h99rQHFvpOX5EijcF37SWw7Fo%2FvfKDTaQZRxRROlw1YZ%2Bwlogl7k%2B3qW%2Bkz43FNwgkKUE33oWzqDMJm%2FPE5dQrALfqpSCB7o2Jfm1447odaYfbf%2FZ5g%3D%3D"}`


