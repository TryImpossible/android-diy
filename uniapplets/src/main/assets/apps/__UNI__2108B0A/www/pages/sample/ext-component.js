!function(t){var e={};function n(o){if(e[o])return e[o].exports;var r=e[o]={i:o,l:!1,exports:{}};return t[o].call(r.exports,r,r.exports,n),r.l=!0,r.exports}n.m=t,n.c=e,n.d=function(t,e,o){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:o})},n.r=function(t){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(t,e){if(1&e&&(t=n(t)),8&e)return t;if(4&e&&"object"==typeof t&&t&&t.__esModule)return t;var o=Object.create(null);if(n.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)n.d(o,r,function(e){return t[e]}.bind(null,r));return o},n.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="",n(n.s=17)}([function(t,e){t.exports={"uni-icon":{fontFamily:"uniicons",fontWeight:"normal"},"uni-bg-red":{backgroundColor:"#F76260",color:"#FFFFFF"},"uni-bg-green":{backgroundColor:"#09BB07",color:"#FFFFFF"},"uni-bg-blue":{backgroundColor:"#007AFF",color:"#FFFFFF"},"uni-container":{flex:1,paddingTop:"15",paddingRight:"15",paddingBottom:"15",paddingLeft:"15",backgroundColor:"#f8f8f8"},"uni-padding-lr":{paddingLeft:"15",paddingRight:"15"},"uni-padding-tb":{paddingTop:"15",paddingBottom:"15"},"uni-header-logo":{paddingTop:"15",paddingRight:"15",paddingBottom:"15",paddingLeft:"15",flexDirection:"column",justifyContent:"center",alignItems:"center",marginTop:"10upx"},"uni-header-image":{width:"80",height:"80"},"uni-hello-text":{marginBottom:"20"},"hello-text":{color:"#7A7E83",fontSize:"14",lineHeight:"20"},"hello-link":{color:"#7A7E83",fontSize:"14",lineHeight:"20"},"uni-panel":{marginBottom:"12"},"uni-panel-h":{backgroundColor:"#ffffff",flexDirection:"row",alignItems:"center",paddingTop:"12",paddingRight:"12",paddingBottom:"12",paddingLeft:"12"},"uni-panel-h-on":{backgroundColor:"#f0f0f0"},"uni-panel-text":{flex:1,color:"#000000",fontSize:"14",fontWeight:"normal"},"uni-panel-icon":{marginLeft:"15",color:"#999999",fontSize:"14",fontWeight:"normal",transform:"rotate(0deg)",transitionDuration:0,transitionProperty:"transform"},"@TRANSITION":{"uni-panel-icon":{duration:0,property:"transform"}},"uni-panel-icon-on":{transform:"rotate(180deg)"},"uni-navigate-item":{flexDirection:"row",alignItems:"center",backgroundColor:"#FFFFFF",borderTopStyle:"solid",borderTopColor:"#f0f0f0",borderTopWidth:"1",paddingTop:"12",paddingRight:"12",paddingBottom:"12",paddingLeft:"12","backgroundColor:active":"#f8f8f8"},"uni-navigate-text":{flex:1,color:"#000000",fontSize:"14",fontWeight:"normal"},"uni-navigate-icon":{marginLeft:"15",color:"#999999",fontSize:"14",fontWeight:"normal"},"uni-list-cell":{position:"relative",flexDirection:"row",justifyContent:"flex-start",alignItems:"center"},"uni-list-cell-pd":{paddingTop:"22upx",paddingRight:"30upx",paddingBottom:"22upx",paddingLeft:"30upx"},"flex-r":{flexDirection:"row"},"flex-c":{flexDirection:"column"},"a-i-c":{alignItems:"center"},"j-c-c":{justifyContent:"center"},"list-item":{flexDirection:"row",paddingTop:"10",paddingRight:"10",paddingBottom:"10",paddingLeft:"10"}}},function(t,e,n){"use strict";function o(t,e,n,o,r,i,a,l,u,f){var c,d="function"==typeof t?t.options:t;if(u){d.components||(d.components={});var p=Object.prototype.hasOwnProperty;for(var s in u)p.call(u,s)&&!p.call(d.components,s)&&(d.components[s]=u[s])}if(f&&((f.beforeCreate||(f.beforeCreate=[])).unshift((function(){this[f.__module]=this})),(d.mixins||(d.mixins=[])).push(f)),e&&(d.render=e,d.staticRenderFns=n,d._compiled=!0),o&&(d.functional=!0),i&&(d._scopeId="data-v-"+i),a?(c=function(t){(t=t||this.$vnode&&this.$vnode.ssrContext||this.parent&&this.parent.$vnode&&this.parent.$vnode.ssrContext)||"undefined"==typeof __VUE_SSR_CONTEXT__||(t=__VUE_SSR_CONTEXT__),r&&r.call(this,t),t&&t._registeredComponents&&t._registeredComponents.add(a)},d._ssrRegister=c):r&&(c=l?function(){r.call(this,this.$root.$options.shadowRoot)}:r),c)if(d.functional){d._injectStyles=c;var g=d.render;d.render=function(t,e){return c.call(e),g(t,e)}}else{var m=d.beforeCreate;d.beforeCreate=m?[].concat(m,c):[c]}return{exports:t,options:d}}n.d(e,"a",(function(){return o}))},function(t,e,n){Vue.prototype.__$appStyle__={},Vue.prototype.__merge_style&&Vue.prototype.__merge_style(n(3).default,Vue.prototype.__$appStyle__)},function(t,e,n){"use strict";n.r(e);var o=n(0),r=n.n(o);for(var i in o)"default"!==i&&function(t){n.d(e,t,(function(){return o[t]}))}(i);e.default=r.a},function(t,e,n){"use strict";var o=n(13),r=n(7),i=n(1),a=Object(i.a)(r.default,o.b,o.c,!1,null,null,"6f26fed8",!1,o.a,void 0);e.default=a.exports},,,function(t,e,n){"use strict";var o=n(8),r=n.n(o);e.default=r.a},function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n={data:function(){return{}},onLoad:function(){},methods:{onTel:function(e){t("log","onTel="+e.detail.tel," at pages/sample/ext-component.nvue:17")}}};e.default=n}).call(this,n(16).default)},,,,,function(t,e,n){"use strict";n.d(e,"b",(function(){return o})),n.d(e,"c",(function(){return r})),n.d(e,"a",(function(){}));var o=function(){var t=this.$createElement,e=this._self._c||t;return e("scroll-view",{staticStyle:{flexDirection:"column"},attrs:{scrollY:!0,showScrollbar:!0,enableBackToTop:!0,bubble:"true"}},[e("div",[e("myText",{staticStyle:{width:"200",height:"100"},attrs:{tel:"12305"},on:{onTel:this.onTel}})],1)])},r=[]},,,function(t,e,n){"use strict";function o(t){var e=Object.prototype.toString.call(t);return e.substring(8,e.length-1)}function r(){return"string"==typeof __channelId__&&__channelId__}Object.defineProperty(e,"__esModule",{value:!0}),e.log=function(t){for(var e=arguments.length,n=new Array(e>1?e-1:0),o=1;o<e;o++)n[o-1]=arguments[o];console[t].apply(console,n)},e.default=function(){for(var t=arguments.length,e=new Array(t),n=0;n<t;n++)e[n]=arguments[n];var i=e.shift();if(r())return e.push(e.pop().replace("at ","uni-app:///")),console[i].apply(console,e);var a=e.map((function(t){var e=Object.prototype.toString.call(t).toLowerCase();if("[object object]"===e||"[object array]"===e)try{t="---BEGIN:JSON---"+JSON.stringify(t)+"---END:JSON---"}catch(e){t="[object object]"}else if(null===t)t="---NULL---";else if(void 0===t)t="---UNDEFINED---";else{var n=o(t).toUpperCase();t="NUMBER"===n||"BOOLEAN"===n?"---BEGIN:"+n+"---"+t+"---END:"+n+"---":String(t)}return t})),l="";if(a.length>1){var u=a.pop();l=a.join("---COMMA---"),0===u.indexOf(" at ")?l+=u:l+="---COMMA---"+u}else l=a[0];console[i](l)}},function(t,e,n){"use strict";n.r(e);n(2);var o=n(4);"undefined"==typeof Promise||Promise.prototype.finally||(Promise.prototype.finally=function(t){var e=this.constructor;return this.then((function(n){return e.resolve(t()).then((function(){return n}))}),(function(n){return e.resolve(t()).then((function(){throw n}))}))}),o.default.mpType="page",o.default.route="pages/sample/ext-component",o.default.el="#root",new Vue(o.default)}]);