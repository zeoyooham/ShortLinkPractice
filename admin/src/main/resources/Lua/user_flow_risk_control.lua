local username=KEYS[1]
local timeWindow=tonumber(ARGV[1])

local accessKey="short-link:user-flow-risk-control:"..username`

local currentAccessAccount=redis.call("INCR",accessKey)

if currentAccessAccount==1 then
    redis.call("EXPIRE",accessKey,timeWindow)

return currentAccessAccount