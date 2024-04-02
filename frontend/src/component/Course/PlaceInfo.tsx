import Logo from '@asset/CourseFree.png'
import { Image } from '@chakra-ui/react'
import { Place } from '@type/course'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

type Props = {
  place: Place
}

export default function PlaceInfo({ place }: Props) {
  console.log(place)
  return (
    <FlexBox w="100%" p="10px" a="center">
      {/* 가게 이미지 */}
      <Image src={Logo} boxSize="100px" borderRadius="10px" />
      {/* 가게 설명 */}
      <FlexBox d="column" p="10px">
        <TextBox
          color="pink300"
          fontWeight="bold"
          typography="t4"
          onClick={() => {
            if (place.url !== ' ') window.open(place.url)
          }}
        >
          {place.name}
        </TextBox>
        <TextBox>주소 : {place.address}</TextBox>
      </FlexBox>
    </FlexBox>
  )
}
